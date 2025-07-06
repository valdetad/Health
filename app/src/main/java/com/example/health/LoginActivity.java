package com.example.health;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button loginButton;
    private Button registerButton;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private DbHelper dbHelper;

    private static final String ERROR_MSG = "Invalid credentials. Please try again.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        dbHelper = new DbHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = editTextUsername.getText().toString().trim();
                String enteredPassword = editTextPassword.getText().toString().trim();

                if (checkUserCredentials(enteredUsername, enteredPassword)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, ERROR_MSG, Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        setupTextWatcher(editTextUsername);
        setupTextWatcher(editTextPassword);
        updateLoginButtonState();
    }

    private void updateLoginButtonState() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        loginButton.setEnabled(!username.isEmpty() && !password.isEmpty());
    }

    private void setupTextWatcher(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateLoginButtonState();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private boolean checkUserCredentials(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        boolean isValid = false;
        try {
            cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?",
                    new String[]{username, password});
            isValid = cursor.moveToFirst();
        } catch (Exception e) {
            Log.e(TAG, "Error checking user credentials", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return isValid;
    }
}
