package t01.users;

public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private Group group;

    public User(int id, String name, String login, String password, Group group) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Group getGroup() {
        return group;
    }
}
