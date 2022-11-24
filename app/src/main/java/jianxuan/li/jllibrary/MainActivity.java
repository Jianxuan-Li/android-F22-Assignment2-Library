package jianxuan.li.jllibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import jianxuan.li.jllibrary.data.Auth;

public class MainActivity extends AppCompatActivity {

    Intent loginIntent, homeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBHelper dbhelper = new DBHelper(this);
        loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        homeIntent = new Intent(MainActivity.this, HomeActivity.class);

        Auth auth = Auth.getInstance();
        if (auth.getStatus()) {
            startActivity(homeIntent);
        } else {
            startActivity(loginIntent);
        }
    }
}