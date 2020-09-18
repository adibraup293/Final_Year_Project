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

    private Boolean validateUsername(){
        String validateUName = usernameEditText.getText().toString();

        if(validateUName.isEmpty()){
            usernameEditText.setError("Field cannot be empty!");
            return false;
        }else if (validateUName.length() >=20){
            usernameEditText.setError("Username too long!");
            return false;
        }else {
            usernameEditText.setError(null);
            return true;
        }
    }

    private Boolean validateEmail(){
        String validateEmail = emailEditText.getText().toString();
        String emailRequirements = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (validateEmail.isEmpty()){
            emailEditText.setError("Email cannot be empty!");
            return false;
        } else if (!validateEmail.matches(emailRequirements)){
            emailEditText.setError("Invalid email address");
            return false;
        } else {
            emailEditText.setError(null);
            return true;
        }
    }

    private Boolean validateName(){
        String validateName = nameEditText.getText().toString();

        if(validateName.isEmpty()){
            nameEditText.setError("Name cannot be empty!");
            return false;
        }else{
            nameEditText.setError(null);
            return true;
        }
    }

    private Boolean validatePassword(){
        String validatePassword = passwordEditText.getText().toString();
        String passwordValidator = "^"+
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    // at least 1 special character
                "(?=\\s+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (validatePassword.isEmpty()){
            passwordEditText.setError("Password cannot be empty");
            return false;
        } else{
            passwordEditText.setError(null);
            return true;
        }
    }

    public void addUser(View view) {

        if (!validateUsername() | !validateEmail() | !validateName() | !validatePassword()){
            return;
        }

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
