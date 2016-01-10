package t01.users;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UserList {
    private static String usersPath = "users.xml";

    private ArrayList<User> users;
    private ArrayList<Group> groups;

    public UserList() {
        users = new ArrayList<>();
        groups = new ArrayList<>();

        DOMParser parser = new DOMParser();
        try (InputStream file = this.getClass().getResourceAsStream(usersPath)) {
            parser.parse(new InputSource(file));
            Document document = parser.getDocument();
            Element root = document.getDocumentElement();
            NodeList nodesGroups = root.getElementsByTagName("group");
            for(int i = 0; i < nodesGroups .getLength(); i++) {
                Element current = (Element) nodesGroups.item(i);
                int id = Integer.parseInt(current.getElementsByTagName("id").item(0).getTextContent().trim());
                String name = current.getElementsByTagName("name").item(0).getTextContent().trim();
                groups.add(new Group(id, name));
            }

            NodeList nodesUsers = root.getElementsByTagName("user");
            for(int i = 0; i < nodesUsers.getLength(); i++) {
                Element current = (Element) nodesUsers.item(i);
                int groupId = Integer.parseInt(current.getAttribute("group-id"));
                int id = Integer.parseInt(current.getElementsByTagName("id").item(0).getTextContent().trim());
                String name = current.getElementsByTagName("name").item(0).getTextContent().trim();
                String login = current.getElementsByTagName("login").item(0).getTextContent().trim();
                String password = current.getElementsByTagName("password").item(0).getTextContent().trim();
                users.add(new User(id, name, login, password, findGroupById(groupId)));
            }

        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private Group findGroupById(int id) {
        for(Group current: groups) {
            if (current.getId() == id){
                return current;
            }
        }
        return null;
    }

    public User findUserByLogin(String login) {
        if (login != null) {
            for (User current : users) {
                if (current.getLogin().equals(login)) {
                    return current;
                }
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
