package com.example.finalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalyearproject.UserHomeActivity;
import com.example.finalyearproject.DatabaseHelper;
import com.example.finalyearproject.R;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    DatabaseHelper databaseHelper;

    Button loginBtn;
    Button loginFBBtn;


    public void LoginButtonUser(View view) {

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        //authenticate User
        User currentUser = databaseHelper.loginUser(new User(username, password));

        //check if authentication is successful or not
        if (currentUser != null) {
            Toast.makeText(this, "Logging in as " + username, Toast.LENGTH_SHORT).show();
            //Officer Logged in successfully

            Intent adminHomeIntent = new Intent(LoginActivity.this, UserHomeActivity.class);
            adminHomeIntent.putExtra("username", username);
            startActivity(adminHomeIntent);
            //

        } else {
            Toast.makeText(this, "No user found. Please create an account!", Toast.LENGTH_LONG).show();
            //User Logged in failed.
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginBtn = findViewById(R.id.loginBtn);
        loginFBBtn = findViewById(R.id.loginFBBtn);

        databaseHelper = new DatabaseHelper(this);
    }
}
