package t01;

import t01.languages.Text;
import t01.templates.MainTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

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
        String locale = (String)session.getAttribute("locale");
        if (locale == null) {
            locale = req.getServletContext().getInitParameter("default-locale");
        }

        PrintWriter out = response.getWriter();
        session.invalidate();

        out.println(MainTemplate.logoutPage(Text.get("logoutpage.title", locale), locale));
    }
}

