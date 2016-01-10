package t01;

import t01.templates.MainTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/lang")
public class LangServlet extends HttpServlet {
    private static String DEFAULT_LOCALE = "en";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest (HttpServletRequest req, HttpServletResponse response) throws IOException {
        String lang = req.getParameter("locale");
        HttpSession session = req.getSession(true);
        if (lang.equals("ru") || lang.equals("en")) {
            session.setAttribute("locale", lang);
        } else {
            session.setAttribute("locale", DEFAULT_LOCALE);
        }
        response.sendRedirect("/");
    }
}

