package t01;

import t01.languages.Text;
import t01.templates.MainTemplate;
import t01.users.User;
import t01.users.UserList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/index.jsp")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest (HttpServletRequest req, HttpServletResponse response) throws IOException {

        HttpSession session = req.getSession(true);
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String locale = (String)session.getAttribute("locale");
        if (locale == null) {
            locale = req.getServletContext().getInitParameter("default-locale");
        }

        boolean alreadyLogin = false;
        String sessionValue = (String)session.getAttribute("login");
        if ( sessionValue != null && sessionValue.equals("true")) {
            alreadyLogin = true;
        }

        UserList users = new UserList();
        User currentUser = null;

        String message = "";
        if (alreadyLogin) {
            message = Text.get("login.already", locale);
            currentUser = users.findUserByLogin((String)session.getAttribute("user"));
        } else {
            if (login != null && password != null) {
                currentUser = users.findUserByLogin(login);
                if (currentUser != null && password.equals(currentUser.getPassword())) {
                    session.setAttribute("user", currentUser.getLogin());
                    session.setAttribute("login", "true");
                    message = Text.get("login.success", locale);
                } else {
                    message = Text.get("login.error", locale);
                }
            }
        }

        PrintWriter out = response.getWriter();
        out.println(MainTemplate.mainPage(
                Text.get("mainpage.title", locale),
                message,
                currentUser,
                users.getUsers(), locale)
        );
    }
}
