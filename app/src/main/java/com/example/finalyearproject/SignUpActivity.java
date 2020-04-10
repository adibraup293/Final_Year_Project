package com.example.finalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalyearproject.User;
import com.example.finalyearproject.DatabaseHelper;
import com.example.finalyearproject.R;

public class SignUpActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    EditText nameEditText;
    EditText emailEditText;

    Button signUpBtn;
    Button signUpFBBtn;

    DatabaseHelper databaseHelper;

    public void addUser(View view) {
        User user = new User();
        user.setUsername(usernameEditText.getText().toString().trim());
        user.setPassword(passwordEditText.getText().toString().trim());
        user.setName(nameEditText.getText().toString().trim());
        user.setEmail(emailEditText.getText().toString().trim());

            databaseHelper.AddUser(user);
            Toast.makeText(this, "User profile created successfully", Toast.LENGTH_SHORT).show();
            finish();

    }

    public void openLoginPage(View view)
    {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUpBtn = findViewById(R.id.signUpBtn);
        signUpFBBtn = findViewById(R.id.signUpFBBtn);

        databaseHelper = new DatabaseHelper(SignUpActivity.this);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
    }

    public void back(View view) {
        finish();
    }
}
