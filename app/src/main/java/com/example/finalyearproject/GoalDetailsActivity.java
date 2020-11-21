package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class GoalDetailsActivity extends AppCompatActivity {

    String userChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_details);

        userChallenge = getIntent().getStringExtra("user_challenge");
        TextView userChallengeText = findViewById(R.id.GoalContent);
        String challengeDetail = userChallenge;
        userChallengeText.setText(challengeDetail);
    }
}