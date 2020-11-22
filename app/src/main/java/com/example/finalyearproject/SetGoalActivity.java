package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SetGoalActivity extends AppCompatActivity {

    String randomChallenge;
    DatabaseHelper databaseHelper;

    public void generateUserGoal(View view){
        Context context = this;
        String[] array = context.getResources().getStringArray(R.array.challenges);
        randomChallenge = array[new Random().nextInt(array.length)];
        TextView generateGoal = findViewById(R.id.GoalText);

        generateGoal.setText(randomChallenge);

        Challenge challenge = new Challenge();
        challenge.setChallenge(generateGoal.getText().toString().trim());
        challenge.setDuration(7);
        challenge.setProgress(0);

        if (generateGoal.getText().toString().trim() == null || generateGoal.getText().toString().trim() == ""){
            Toast.makeText(this, "Please generate a challenge!", Toast.LENGTH_SHORT).show();
        }
        databaseHelper.addChallenge(challenge);
        Toast.makeText(this, "Challenge successfully saved", Toast.LENGTH_SHORT).show();
        finish();

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

        databaseHelper = new DatabaseHelper(this);

    }
}
