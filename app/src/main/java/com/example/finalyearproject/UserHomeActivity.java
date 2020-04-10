package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.finalyearproject.MainActivity;
import com.example.finalyearproject.R;;

public class UserHomeActivity extends AppCompatActivity {

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

    public void back(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        String username = getIntent().getStringExtra("username");
        TextView userText = findViewById(R.id.userNameTextView);
        String welcomeMsg = "Welcome back " + username;
        userText.setText(welcomeMsg);
    }
}
