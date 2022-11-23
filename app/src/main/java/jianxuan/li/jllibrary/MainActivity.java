package jianxuan.li.jllibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Intent loginIntent, homeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBHelper dbhelper = new DBHelper(this);

        setContentView(R.layout.activity_main);

        loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        homeIntent = new Intent(MainActivity.this, HomeActivity.class);


    }
}