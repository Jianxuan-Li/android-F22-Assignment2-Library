package jianxuan.li.jllibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jianxuan.li.jllibrary.data.Auth;
import jianxuan.li.jllibrary.data.UserModel;

public class LoginActivity extends AppCompatActivity {

    Button loginButton, registerButton;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // widgets
        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        // on click login button show a toast
        loginButton.setOnClickListener(v -> {
            UserModel userModel = new UserModel(this);

            String username = this.username.getText().toString();
            String password = this.password.getText().toString();

            if (userModel.checkLogin(username, password)) {
                Auth auth = Auth.getInstance();
                auth.login();
                auth.setUsername(username);


                Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);

                // save username to shared preference
                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username);
                editor.apply();

                startActivity(homeIntent);
            } else {
                Toast.makeText(this, "Wrong identity", Toast.LENGTH_SHORT).show();
            }
        });

        // on click register button go to register activity
        registerButton.setOnClickListener(v -> {
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        });
    }
}