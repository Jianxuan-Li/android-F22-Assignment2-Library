package jianxuan.li.jllibrary.data;

// singleton to save the auth status
// Path: app\src\main\java\jianxuan\li\jllibrary\data\DBHelper.java
public class Auth {
    private static Auth instance;
    private boolean status = false;
    private String username;

    private Auth() {
    }

    public static Auth getInstance() {
        if (instance == null) {
            instance = new Auth();
        }
        return instance;
    }

    public boolean getStatus() {
        return status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public boolean login(){
        status = true;
        return true;
    }

    public boolean logout(){
        status = false;
        return true;
    }
}