package com.group0562.adventureofpost.trivia.ui;

//import statements
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.trivia.Question;
import com.group0562.adventureofpost.trivia.TriviaPresenter;

public class TriviaActivity extends AppCompatActivity {

    /**
     * an instance of Trivia
     */
    TriviaPresenter presenter;

    //TODO implement later a database check to see if theres a previous saveState
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        int diff = 1;
        int op = 2;
        if (getIntent().hasExtra("diff")) {
            switch (getIntent().getStringExtra("diff")) {
                case "Easy":
                    diff = 1;
                    break;
                case "Medium":
                    diff = 2;
                    break;
                default:
                    diff = 3;
                    break;
            }
        }
        if (getIntent().hasExtra("op")) {
            switch (getIntent().getStringExtra("op")) {
                case "Addition":
                    op = 1;
                    break;
                case "Subtraction":
                    op = 2;
                    break;
                default:
                    op = 3;
                    break;
            }
        }

        if (getIntent().hasExtra("saveState")) {
            presenter = new TriviaPresenter(getIntent().getStringExtra("username"), getIntent().getStringExtra("saveState"));
        } else {
            presenter = new TriviaPresenter(getIntent().getStringExtra("username"), op, diff);
        }
        if (getIntent().hasExtra("color")) {
            presenter.setBackgroundColor(getIntent().getStringExtra("color"));
        }
        switch (presenter.getBackgroundColor()) {
            case "White":
                setActivityBackgroundColor(0xFFFFFFFF);
                break;
            case "Blue":
                setActivityBackgroundColor(0xFFB8D5D6);
                break;
            case "Green":
                setActivityBackgroundColor(0xFF00FF00);
                break;
            case "Yellow":
                setActivityBackgroundColor(0xFFFFFF00);
                break;
        }
        onClickOptionHelper();

    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    /**
     * Handles case where user clicks first button
     *
     * @param v the view
     */
    public void onClickA(View v) {
        presenter.onClick(0);
        onClickOptionHelper();
    }

    /**
     * Handles case where user clicks second button
     *
     * @param v the view
     */
    public void onClickB(View v) {
        presenter.onClick(1);
        onClickOptionHelper();
    }

    /**
     * Handles case where user clicks third button
     *
     * @param v the view
     */
    public void onClickC(View v) {
        presenter.onClick(2);
        onClickOptionHelper();
    }

    /**
     * Handles case where user clicks fourth button
     *
     * @param v the view
     */
    public void onClickD(View v) {
        presenter.onClick(3);
        onClickOptionHelper();
    }

    /**
     * Semi-universal button helper function that updates the button, scores and question
     */
    public void onClickOptionHelper() {
        if (presenter.hasNext()) {
            Question q = presenter.getQuestion();
            String[] s = q.getOptions();

            TextView mTextView = findViewById(R.id.textView3);
            mTextView.setText(q.getQuestion());

            Button mTextView1 = findViewById(R.id.button4);
            mTextView1.setText(s[0]);

            Button mTextView2 = findViewById(R.id.button5);
            mTextView2.setText(s[1]);

            Button mTextView3 = findViewById(R.id.button6);
            mTextView3.setText(s[2]);

            Button mTextView4 = findViewById(R.id.button7);
            mTextView4.setText(s[3]);
        } else {
            Intent intent = new Intent(this, TriviaEndActivity.class);
            intent.putExtra("stats", presenter.getStats());
            startActivity(intent);
        }
    }

    public void onClickSettings(View view) {
        Intent intent = new Intent(this, TriviaSettingsActivity.class);
        String saveState = presenter.saveGame();
        intent.putExtra("save", saveState);
        startActivity(intent);
    }

    /**
     * Store the current game's info into user-specific data
     * TODO complete this function after database is figured out
     */
    public void onClickPause(View view) {
        Intent intent = new Intent(this, TriviaPauseActivity.class);
        intent.putExtra("save", presenter.saveGame());
        intent.putExtra("stats", presenter.getStats());
        startActivity(intent);
    }

}
