package jianxuan.li.jllibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jianxuan.li.jllibrary.data.Auth;
import jianxuan.li.jllibrary.data.UserModel;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    Intent homeIntent;

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // widgets
        loginButton = findViewById(R.id.login);
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

                this.username.setText("");
                this.password.setText("");

                homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(homeIntent);
            } else {
                Toast.makeText(this, "Wrong identity", Toast.LENGTH_SHORT).show();
            }
        });
    }
}