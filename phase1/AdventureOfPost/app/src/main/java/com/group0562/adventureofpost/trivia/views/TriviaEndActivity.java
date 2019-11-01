package com.group0562.adventureofpost.trivia.views;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.R;

public class TriviaEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_end);
        int[] values = getIntent().getIntArrayExtra("stats");
        String correct, incorrect, score;

        correct = "correct: " + values[0];
        incorrect = "Incorrect: " + values[1];
        score = "Score: " + values[2];

        TextView correctTextView = (TextView) findViewById(R.id.Correct);
        correctTextView.setText(correct);
        TextView incorrectTextView = (TextView) findViewById(R.id.Incorrect);
        incorrectTextView.setText(incorrect);
        TextView scoreTextView = (TextView) findViewById(R.id.Score);
        scoreTextView.setText(score);

    }
}
