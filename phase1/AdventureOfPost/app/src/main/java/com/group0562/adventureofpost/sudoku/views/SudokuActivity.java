package com.group0562.adventureofpost.sudoku.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.group0562.adventureofpost.R;

public class SudokuActivity extends AppCompatActivity {

    private BoardComp gameBoard;

    private TextView hintsLeft;

    private TextView timerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        // Initialize game board
        gameBoard = findViewById(R.id.board);

    }


}
