package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextUsername, editTextEmail, editTextPassword, editTextConfirmPassword;
    Button buttonRegister;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextRegConfirmPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.textViewExistingUser);

        buttonRegister.setOnClickListener(v -> {
                String username = editTextUsername.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();

                if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
                    if (password.equals(confirmPassword)) {
                        if (!isValidPassword(password)) {
                            Toast.makeText(RegisterActivity.this, "Password must be at least 8 characters long and contain at least 1 letter, 1 digit, and 1 special character", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                            db.registerUser(username, email, password);
                            Toast.makeText(RegisterActivity.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                }
            }
        );

        tv.setOnClickListener(v ->
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class))
        );
    }

    public static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean condition1 = false; // At least 1 letter
        boolean condition2 = false; // At least 1 digit
        boolean condition3 = false; // At least 1 special character

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isLetter(c)) {
                condition1 = true;
            }
            else if (Character.isDigit(c)) {
                condition2 = true;
            }
            else if (!Character.isLetterOrDigit(c)) {
                condition3 = true;
            }
        }

        return (condition1 && condition2 && condition3);
    }
}