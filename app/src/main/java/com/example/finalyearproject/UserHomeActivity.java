package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalyearproject.MainActivity;
import com.example.finalyearproject.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;;

public class UserHomeActivity extends AppCompatActivity {

//    private LoginButton loginFacebookButton;
    Button LogoutButton;
    Button connectFB;

    public void openSetGoalPage(View view)
    {
        Intent intent = new Intent(UserHomeActivity.this, SetGoalActivity.class);
        startActivity(intent);
    }

    public void openGoalProgressPage(View view)
    {
        Intent intent = new Intent(UserHomeActivity.this, CheckGoalsProgressActivity.class);
        startActivity(intent);
    }

    public void openRewardsShopPage(View view)
    {
        Intent intent = new Intent(UserHomeActivity.this, RewardShopActivity.class);
        startActivity(intent);
    }

    public void openFriendsListPage(View view)
    {
        Intent intent = new Intent(UserHomeActivity.this, FriendsListActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(UserHomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

//    private void logout(View view){
//        LoginManager.getInstance().logOut();
//        Intent intent = new Intent(UserHomeActivity.this, LoginActivity.class);
//        startActivity(intent);
//        finish();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        String username = getIntent().getStringExtra("username");
        TextView userText = findViewById(R.id.userNameTextView);
        String welcomeMsg = "Welcome back " + username + "!";
        userText.setText(welcomeMsg);

//        loginFacebookButton = findViewById(R.id.loginFBBtn);
        LogoutButton = findViewById(R.id.logoutBtn);
        connectFB = findViewById(R.id.connectToFBBtn);

        //token for login
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if (isLoggedIn == true){
            connectFB.setVisibility(View.INVISIBLE);
        }else{
            connectFB.setVisibility(View.VISIBLE);
        }

    }
}
