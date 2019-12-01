package com.group0562.adventureofpost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.shapeClicker.ui.SCSettingActivity;
import com.group0562.adventureofpost.sudoku.ui.SudokuStartActivity;
import com.group0562.adventureofpost.trivia.ui.TriviaActivity;
import com.group0562.adventureofpost.trivia.ui.TriviaStartActivity;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView welcome = findViewById(R.id.welcomeMessage);
        String username = getIntent().getStringExtra("username");
        String welcomeMessage = getResources().getString(R.string.welcome_message);
        welcome.setText(String.format(welcomeMessage, username));
    }

    public void onClickShapeClicker(View view) {
        Intent intent = new Intent(this, SCSettingActivity.class);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }

    public void onClickSudoku(View view) {
        Intent intent = new Intent(this, SudokuStartActivity.class);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }

    public void onClickTrivia(View view) {
        Intent intent;
        if (getIntent().hasExtra("saveState")){
            intent = new Intent(this, TriviaActivity.class);
            intent.putExtra("saveState", getIntent().getStringExtra("saveState"));
        } else {
            intent = new Intent(this, TriviaStartActivity.class);
        }
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }
}
