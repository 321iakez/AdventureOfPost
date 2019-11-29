package com.group0562.adventureofpost.trivia.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.trivia.Trivia;

public class TriviaPauseActivity extends AppCompatActivity {
    Trivia game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_pause);

        game = (Trivia)getIntent().getSerializableExtra("game");
        int[] values = game.getStats();

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
        intent.putExtra("trivia", game);
        intent.putExtra("triviaSave", game.saveGame());
        startActivity(intent);
    }

    public void onClickResume(View view){
        Intent intent = new Intent(this, TriviaActivity.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }
}
