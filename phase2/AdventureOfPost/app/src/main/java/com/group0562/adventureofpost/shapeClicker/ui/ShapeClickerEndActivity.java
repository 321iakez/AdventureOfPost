package com.group0562.adventureofpost.shapeClicker.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.sudoku.ui.SudokuScoreboardActivity;

public class ShapeClickerEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_clicker_end);

        addListenerReturnHome();
        addListenerScoreboard();
    }

    /*to go back to the page where you can select games*/
    private void addListenerReturnHome() {
        findViewById(R.id.return_home).setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);
        });
    }

    private void addListenerScoreboard() {
        findViewById(R.id.sc_scoreboard).setOnClickListener(v -> {
            Intent intent = new Intent(this, SudokuScoreboardActivity.class);
            intent.putExtra("currGame", "shapeClicker");
            intent.putExtra("username", intent.getStringExtra("username"));
            startActivity(intent);
        });
    }
}
