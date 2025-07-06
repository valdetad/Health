package com.example.health;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button registerButton;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        registerButton = findViewById(R.id.registerButton);

        dbHelper = new DbHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    showToast("Please enter both username and password");
                } else {
                    long userId = addUserToDatabase(username, password);
                    if (userId != -1) {
                        showToast("Registration successful");
                        Log.d(TAG, "User registered with username: " + username);
                        finish(); // Close registration activity
                    } else {
                        showToast("Registration failed");
                        Log.d(TAG, "User registration failed for username: " + username);
                    }
                }
            }
        });
    }

    private long addUserToDatabase(String username, String password) {
        return dbHelper.addUser(username, password);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
