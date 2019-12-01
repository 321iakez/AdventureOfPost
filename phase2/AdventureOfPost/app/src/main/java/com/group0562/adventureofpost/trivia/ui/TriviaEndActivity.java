package com.group0562.adventureofpost.trivia.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.trivia.Trivia;
import com.group0562.adventureofpost.trivia.TriviaPresenter;

/**
 * This is the end screen for the Trivia game
 * It will display the end stats of the user once they have completed the game
 */
public class TriviaEndActivity extends AppCompatActivity {

    /**
     * Displays stats of user after they have completed all 10 questions
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_end);
        TriviaPresenter presenter = new TriviaPresenter(getIntent().getStringExtra("username"), getIntent().getStringExtra("saveState"));
        presenter.saveToDatabase(this);
        int[] values = presenter.getStats();
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

    /**
     * Returns user to puzzles screen
     *
     * @param view the view
     */
    public void onClickNext(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }
}
