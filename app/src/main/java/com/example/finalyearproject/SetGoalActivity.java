package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class SetGoalActivity extends AppCompatActivity {

    String randomChallenge;

    public void generateUserGoal(View view){
        Context context = this;
        String[] array = context.getResources().getStringArray(R.array.challenges);
        randomChallenge = array[new Random().nextInt(array.length)];
        TextView generateGoal = findViewById(R.id.GoalText);

        generateGoal.setText(randomChallenge);

    }

    public void Reset (View view){
        TextView generateGoal = findViewById(R.id.GoalText);
        generateGoal.setText("");
    }

    public void SaveChallenge (View view){

    }

    public void back(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);

    }
}
