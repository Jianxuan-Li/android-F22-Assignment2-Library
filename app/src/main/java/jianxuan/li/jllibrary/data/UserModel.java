package jianxuan.li.jllibrary.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import jianxuan.li.jllibrary.DBHelper;

public class UserModel {
    private String username;
    private String password;
    private final Context context;

    public UserModel(Context cont) {
        context = cont;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkLogin(String username, String password){
        DBHelper dbhelper = new DBHelper(context);

        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String query = "SELECT * FROM User WHERE username = '" + username + "' AND password = '" + password + "'";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }

        cursor.close();
        return true;
    }

}
