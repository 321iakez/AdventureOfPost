package com.group0562.adventureofpost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.shapeClicker.ui.SCSettingActivity;
import com.group0562.adventureofpost.sudoku.ui.SudokuStartActivity;
import com.group0562.adventureofpost.trivia.Trivia;
import com.group0562.adventureofpost.trivia.ui.TriviaStartActivity;

public class GameActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.group0562.AdventureOfPost.MESSAGE";
    private Trivia trivia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void onClickShapeClicker(View view) {
        Intent intent = new Intent(this, SCSettingActivity.class);
        TextView textView = findViewById(R.id.CCButton);
        String message = textView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void onClickSudoku(View view) {
        Intent intent = new Intent(this, SudokuStartActivity.class);
        TextView textView = findViewById(R.id.SudokuButton);
        String message = textView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void onClickTrivia(View view) {
        Intent intent = new Intent(this, TriviaStartActivity.class);
        TextView textView = findViewById(R.id.TriviaButton);
        String message = textView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
}
