package jianxuan.li.jllibrary.data;

public class Auth {
    private String username;
    private String password;
    private String emailId;
    private String token;

    public Auth(String username, String password, String emailId) {
        this.username = username;
        this.password = password;
        this.emailId = emailId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
