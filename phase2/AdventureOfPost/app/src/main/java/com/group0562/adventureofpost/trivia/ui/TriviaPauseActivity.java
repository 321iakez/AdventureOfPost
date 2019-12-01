package com.group0562.adventureofpost.trivia.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.R;

public class TriviaPauseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_pause);

        int[] values = getIntent().getIntArrayExtra("stats");

        //the player stats
        String correct, incorrect, score;
        correct = "correct: " + values[0];
        incorrect = "Incorrect: " + values[1];
        score = "Score: " + values[2];


        //display the player stats on the user screen
        TextView correctTextView = findViewById(R.id.Correct);
        correctTextView.setText(correct);
        TextView incorrectTextView = findViewById(R.id.Incorrect);
        incorrectTextView.setText(incorrect);
        TextView scoreTextView = findViewById(R.id.Score);
        scoreTextView.setText(score);
    }

    public void onClickSave(View view){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("triviaSave", getIntent().getStringExtra("save"));
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }

    public void onClickResume(View view){
        Intent intent = new Intent(this, TriviaActivity.class);
        intent.putExtra("saveState", getIntent().getStringExtra("save"));
        startActivity(intent);
    }
}
