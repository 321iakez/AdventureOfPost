package com.group0562.adventureofpost.trivia.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.trivia.Question;
import com.group0562.adventureofpost.trivia.Trivia;

import java.io.*;

public class TriviaActivity extends AppCompatActivity{

    Trivia game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
    }

    public void onClickA(View v) {
        game.updatePoints(0);
        if (game.hasNext()){
            Question q = game.getQuestion();
            String[] s = q.getOptions();
            TextView mTextView = (TextView) findViewById(R.id.textView3);
            mTextView.setText(q.getQuestion());
            TextView mTextView1 = (TextView) findViewById(R.id.button4);
            mTextView1.setText(s[0]);
            TextView mTextView2 = (TextView) findViewById(R.id.button5);
            mTextView2.setText(s[1]);
            TextView mTextView3 = (TextView) findViewById(R.id.button6);
            mTextView3.setText(s[2]);
            TextView mTextView4 = (TextView) findViewById(R.id.button7);
            mTextView4.setText(s[3]);
        } else {
            Intent intent = new Intent(this, TriviaEndActivity.class);
            int[] stats = game.getStats();
            intent.putExtra("stats", stats);
            startActivity(intent);
        }
    }

    public void onClickB(View v) {
        game.updatePoints(1);
        if (game.hasNext()){
            Question q = game.getQuestion();
            String[] s = q.getOptions();
            TextView mTextView = (TextView) findViewById(R.id.textView3);
            mTextView.setText(q.getQuestion());
            TextView mTextView1 = (TextView) findViewById(R.id.button4);
            mTextView1.setText(s[0]);
            TextView mTextView2 = (TextView) findViewById(R.id.button5);
            mTextView2.setText(s[1]);
            TextView mTextView3 = (TextView) findViewById(R.id.button6);
            mTextView3.setText(s[2]);
            TextView mTextView4 = (TextView) findViewById(R.id.button7);
            mTextView4.setText(s[3]);
        } else {
            Intent intent = new Intent(this, TriviaEndActivity.class);
            int[] stats = game.getStats();
            intent.putExtra("stats", stats);
            startActivity(intent);
        }
    }

    public void onClickC(View v) {
        game.updatePoints(2);
        if (game.hasNext()){
            Question q = game.getQuestion();
            String[] s = q.getOptions();
            TextView mTextView = (TextView) findViewById(R.id.textView3);
            mTextView.setText(q.getQuestion());
            TextView mTextView1 = (TextView) findViewById(R.id.button4);
            mTextView1.setText(s[0]);
            TextView mTextView2 = (TextView) findViewById(R.id.button5);
            mTextView2.setText(s[1]);
            TextView mTextView3 = (TextView) findViewById(R.id.button6);
            mTextView3.setText(s[2]);
            TextView mTextView4 = (TextView) findViewById(R.id.button7);
            mTextView4.setText(s[3]);
        } else {
            Intent intent = new Intent(this, TriviaEndActivity.class);
            int[] stats = game.getStats();
            intent.putExtra("stats", stats);
            startActivity(intent);
        }
    }

    public void onClickD(View v) {
        game.updatePoints(3);
        if (game.hasNext()){
            Question q = game.getQuestion();
            String[] s = q.getOptions();
            TextView mTextView = (TextView) findViewById(R.id.textView3);
            mTextView.setText(q.getQuestion());
            TextView mTextView1 = (TextView) findViewById(R.id.button4);
            mTextView1.setText(s[0]);
            TextView mTextView2 = (TextView) findViewById(R.id.button5);
            mTextView2.setText(s[1]);
            TextView mTextView3 = (TextView) findViewById(R.id.button6);
            mTextView3.setText(s[2]);
            TextView mTextView4 = (TextView) findViewById(R.id.button7);
            mTextView4.setText(s[3]);
        } else {
            Intent intent = new Intent(this, TriviaEndActivity.class);
            int[] stats = game.getStats();
            intent.putExtra("stats", stats);
            startActivity(intent);
        }
    }

    public void onClickStart(View v) throws IOException{
        game = new Trivia();
        Question q = game.getQuestion();
        String[] s = q.getOptions();
        TextView mTextView = (TextView) findViewById(R.id.textView3);
        mTextView.setText(q.getQuestion());
        TextView mTextView1 = (TextView) findViewById(R.id.button4);
        mTextView1.setText(s[0]);
        TextView mTextView2 = (TextView) findViewById(R.id.button5);
        mTextView2.setText(s[1]);
        TextView mTextView3 = (TextView) findViewById(R.id.button6);
        mTextView3.setText(s[2]);
        TextView mTextView4 = (TextView) findViewById(R.id.button7);
        mTextView4.setText(s[3]);
    }

}
