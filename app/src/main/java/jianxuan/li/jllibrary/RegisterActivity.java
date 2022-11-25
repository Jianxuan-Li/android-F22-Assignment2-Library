package jianxuan.li.jllibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jianxuan.li.jllibrary.data.Auth;
import jianxuan.li.jllibrary.data.UserModel;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, email;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // widgets
        username = findViewById(R.id.etName);
        password = findViewById(R.id.etPassword);
        email = findViewById(R.id.etEmail);
        registerButton = findViewById(R.id.btnRegister);

        // on click register button
        registerButton.setOnClickListener(v -> {
            UserModel userModel = new UserModel(this);
            String username = this.username.getText().toString();
            String password = this.password.getText().toString();
            String email = this.email.getText().toString();

            // validate
            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userModel.register(username, password, email)) {
                Auth auth = Auth.getInstance();
                auth.setUsername(username);
                auth.login();
                Toast.makeText(this, "Register success", Toast.LENGTH_SHORT).show();

                // go to home activity
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Account already exists", Toast.LENGTH_SHORT).show();
            }
        });
    }
}