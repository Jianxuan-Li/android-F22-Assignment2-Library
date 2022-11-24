package jianxuan.li.jllibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import jianxuan.li.jllibrary.data.Auth;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    Intent homeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // widgets
        loginButton = findViewById(R.id.login);

        // on click login button show a toast
        loginButton.setOnClickListener(v -> {
            Auth auth = Auth.getInstance();
            auth.login();

            homeIntent = new Intent(this, HomeActivity.class);
            startActivity(homeIntent);
        });
    }
}