package webubb.domain;

public class User {
    private int id;
    private String username;
    private String password;
    private String route;

    public User(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
        this.route = "";
    }

    public int getId() { return id; }

    public void setId(int id) { this.id=id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoute() { return route; }

    public void setRoute(int route) { this.route += route + " "; }

    public void resetRoute() { this.route = ""; }

}