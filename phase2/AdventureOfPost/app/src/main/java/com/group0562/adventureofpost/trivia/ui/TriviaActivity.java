package com.group0562.adventureofpost.trivia.ui;

//import statements
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.trivia.Question;
import com.group0562.adventureofpost.trivia.Trivia;
import com.group0562.adventureofpost.trivia.TriviaPresenter;
import com.group0562.adventureofpost.trivia.TriviaStats;

public class TriviaActivity extends AppCompatActivity{

    /**
    * an instance of Trivia
    */
    TriviaPresenter presenter;

    //TODO implement later a database check to see if theres a previous saveState
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        int diff = Integer.parseInt(getIntent().getStringExtra("diff"));
        int op = Integer.parseInt(getIntent().getStringExtra("op"));
        presenter = new TriviaPresenter(this,new TriviaStats(100), op, diff);

    }

    /**
     * Handles case where user clicks first button
     * @param v the view
     */
    public void onClickA(View v) {
        presenter.onClick(0);
        onClickOptionHelper();
    }

    /**
     * Handles case where user clicks second button
     * @param v the view
     */
    public void onClickB(View v) {
        presenter.onClick(1);
        onClickOptionHelper();
    }

    /**
     * Handles case where user clicks third button
     * @param v the view
     */
    public void onClickC(View v) {
        presenter.onClick(2);
        onClickOptionHelper();
    }

    /**
     * Handles case where user clicks fourth button
     * @param v the view
     */
    public void onClickD(View v) {
        presenter.onClick(3);
        onClickOptionHelper();
    }

    /**
     * Semi-universal button helper function that updates the button, scores and question
     */
    public void onClickOptionHelper(){
        if (presenter.hasNext()){
            Question q = presenter.getQuestion();
            String[] s = q.getOptions();
            TextView mTextView = findViewById(R.id.textView3);
            mTextView.setText(q.getQuestion());
            TextView mTextView1 = findViewById(R.id.button4);
            mTextView1.setText(s[0]);
            TextView mTextView2 = findViewById(R.id.button5);
            mTextView2.setText(s[1]);
            TextView mTextView3 = findViewById(R.id.button6);
            mTextView3.setText(s[2]);
            TextView mTextView4 = findViewById(R.id.button7);
            mTextView4.setText(s[3]);
        } else {
            Intent intent = new Intent(this, TriviaEndActivity.class);
            startActivity(intent);
        }
    }

    public void onClickSettings(View view){
        Intent intent = new Intent(this, TriviaSettingsActivity.class);
        String saveState = presenter.saveGame();
        intent.putExtra("save", saveState);
        startActivity(intent);
    }

    /**
     * Store the current game's info into user-specific data
     * TODO complete this function after database is figured out
     */
    public void onClickPause(View view){
        Intent intent = new Intent(this, TriviaPauseActivity.class);
        startActivity(intent);
    }




}
