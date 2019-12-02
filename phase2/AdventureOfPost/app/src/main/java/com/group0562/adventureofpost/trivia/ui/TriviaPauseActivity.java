package com.group0562.adventureofpost.trivia.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.trivia.TriviaPresenter;

/**
 * This is the Pause screen for the trivia game
 * It will let the user select if they would like to save
 * the current game and exit to main menu or if they would
 * like to resume the game
 */
public class TriviaPauseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_pause);

        int[] values = getIntent().getIntArrayExtra("stats");

        //the player stats to be displayed
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

    /**
     * Method to save game information
     *
     * @param view view from previous activity
     */
    public void onClickSave(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        TriviaPresenter presenter = new TriviaPresenter(getIntent().getStringExtra("username"), getIntent().getStringExtra("saveState"));
        presenter.insertToDatabase(this, getIntent().getStringExtra("saveState"), getIntent().getStringExtra("username"));
        intent.putExtra("saveState", getIntent().getStringExtra("saveState"));
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }

    /**
     * Method to resume current game
     *
     * @param view view from previous activity
     */
    public void onClickResume(View view) {
        Intent intent = new Intent(this, TriviaActivity.class);
        intent.putExtra("saveState", getIntent().getStringExtra("saveState"));
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }
}
