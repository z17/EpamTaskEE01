package t01.templates;

import t01.languages.Text;
import t01.users.User;

import java.util.ArrayList;

public class MainTemplate {
    static private String header(String name) {
        return "<html>" +
                "<head>" +
                "<title>" + name + "</title>" +
                "</head>";
    }

    static private String footer(String locale) {
        return "<a href='/lang?locale=ru'>RU</a> | <a href='/lang?locale=en'>EN</a>" +
                "<br><a href='/logout'>"+ Text.get("logout.link", locale) +"</a>" +
                "</html>";
    }

    static public String mainPage(String name, String message, User current, ArrayList<User> list, String locale) {
        StringBuilder html = new StringBuilder(header(name) +
                "<body>" +
                "<form method='POST'>" +
                "<input type='text' name='login' /><br>" +
                "<input type='text' name='password' /><br>" +
                "<input type='submit' />" +
                "</form>" +
                "<p>" + message + "</p>"
        );

        if (current != null) {
            html.append("<h2>" + Text.get("user.current-name", locale) + "</h2>");
            html.append("<p>" + Text.get("user.name", locale) + ": ")
                    .append(current.getName())
                    .append("<br>")
                    .append(Text.get("user.login", locale) + ": ")
                    .append(current.getLogin())
                    .append("<br>")
                    .append(Text.get("user.group", locale) + ": ")
                    .append(current.getGroup().getName())
                    .append("<br>")
                    .append("</p>");
        }

        if (list != null) {
            html.append("<h2>" + Text.get("user.list-name", locale) + "</h2>");
            for (User user : list) {
                html.append("<p>" + Text.get("user.name", locale) + ": ")
                        .append(user.getName())
                        .append("<br>")
                        .append(Text.get("user.login", locale) + ": ")
                        .append(user.getLogin())
                        .append("<br>")
                        .append(Text.get("user.group", locale) + ": ")
                        .append(user.getGroup().getName())
                        .append("<br>")
                        .append("</p>");
            }
        }

        html.append("</body>").append(footer(locale));
        return html.toString();
    }

    static public String logoutPage(String name, String locale) {
        return header(name) +
                "<body>" +
                "<p>" + Text.get("logout.message", locale) + "</p>" +
                "</body>" +
                footer(locale);

    }
}
