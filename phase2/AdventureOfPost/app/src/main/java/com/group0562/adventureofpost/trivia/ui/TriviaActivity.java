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
        if (getIntent().hasExtra("backgroundColor")) {
            presenter.setBackgroundColor(getIntent().getStringExtra("backgroundColor"));
        }
        if (getIntent().hasExtra("buttonColor")) {
            presenter.setButtonColor(getIntent().getStringExtra("buttonColor"));
        }
        if (getIntent().hasExtra("textColor")) {
            presenter.setTextColor(getIntent().getStringExtra("textColor"));
        }
        setActivityBackgroundColor();
        onClickOptionHelper();

    }

    public void setActivityBackgroundColor() {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getColorValue(presenter.getBackgroundColor()));
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

    int getColorValue(String color) {
        int colorValue = 0xFFFFFFFF;
        switch(color) {
            case "Black":
                colorValue = 0XFF000000;
                break;
            case "White":
                colorValue = 0xFFFFFFFF;
                break;
            case "Blue":
                colorValue = 0xFFB8D5D6;
                break;
            case "Green":
                colorValue =  0xFF00FF00;
                break;
            case "Yellow":
                colorValue = 0xFFFFFF00;
                break;
        }
        return colorValue;
    }
    /**
     * Semi-universal button helper function that updates the button, scores and question
     */
    public void onClickOptionHelper() {
        if (presenter.hasNext()) {
            Question q = presenter.getQuestion();
            String[] s = q.getOptions();

            TextView question = findViewById(R.id.textView3);
            question.setText(q.getQuestion());
            question.setTextColor(getColorValue(presenter.getTextColor()));

            Button buttonA = findViewById(R.id.button4);
            buttonA.setText(s[0]);
            buttonA.setTextColor(getColorValue(presenter.getTextColor()));
            buttonA.setBackgroundColor(getColorValue(presenter.getButtonColor()));


            Button buttonB = findViewById(R.id.button5);
            buttonB.setText(s[1]);
            buttonB.setTextColor(getColorValue(presenter.getTextColor()));
            buttonB.setBackgroundColor(getColorValue(presenter.getButtonColor()));

            Button buttonC = findViewById(R.id.button6);
            buttonC.setText(s[2]);
            buttonC.setTextColor(getColorValue(presenter.getTextColor()));
            buttonC.setBackgroundColor(getColorValue(presenter.getButtonColor()));

            Button buttonD = findViewById(R.id.button7);
            buttonD.setText(s[3]);
            buttonD.setTextColor(getColorValue(presenter.getTextColor()));
            buttonD.setBackgroundColor(getColorValue(presenter.getButtonColor()));


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
