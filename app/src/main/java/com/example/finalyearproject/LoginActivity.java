package com.example.finalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalyearproject.UserHomeActivity;
import com.example.finalyearproject.DatabaseHelper;
import com.example.finalyearproject.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    DatabaseHelper databaseHelper;

    Button loginBtn;
    private LoginButton loginFacebookButton;

    private CallbackManager callbackManager;

    public void LoginButtonUser(View view) {

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();


        //authenticate User
        User currentUser = databaseHelper.loginUser(new User(username, password));

        if (username == "admin" && password == "admin" || username == "Admin" && password == "Admin") {
            Intent userHomeIntent = new Intent(LoginActivity.this, AdminHomeActivity.class);
            startActivity(userHomeIntent);
        }

        //check if authentication is successful or not
        else if (currentUser != null) {
            Toast.makeText(this, "Logging in as " + username, Toast.LENGTH_SHORT).show();
            //User Logged in successfully

            Intent userHomeIntent = new Intent(LoginActivity.this, UserHomeActivity.class);
            userHomeIntent.putExtra("username", username);
            startActivity(userHomeIntent);

        } else {
            Toast.makeText(this, "No user found. Please create an account!", Toast.LENGTH_LONG).show();
            //User Logged in failed.
        }
    }

    public void openSignUpPage(View view)
    {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginBtn = findViewById(R.id.loginBtn);
        loginFacebookButton = findViewById(R.id.loginFBBtn);

        databaseHelper = new DatabaseHelper(this);

        callbackManager = CallbackManager.Factory.create();
        //loginFacebookButton.setPermissions(Arrays.asList("email, public_profile"));

        //token for login
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if (isLoggedIn == true){
            Intent userHomeIntent = new Intent(LoginActivity.this, UserHomeActivity.class);
            String username = "";
            userHomeIntent.putExtra("username", username);
            startActivity(userHomeIntent);
        }

        loginFacebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String username = "";
                //Toast.makeText(this, "Logging in as " + username, Toast.LENGTH_SHORT).show();
                //User Logged in successfully

                Intent userHomeIntent = new Intent(LoginActivity.this, UserHomeActivity.class);
                userHomeIntent.putExtra("username", username);
                startActivity(userHomeIntent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void back(View view) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
