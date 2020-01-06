package com.group0562.adventureofpost.sudoku.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.ScoreboardActivity;

public class SudokuStartActivity extends AppCompatActivity {

    private final String[] GRID_OPTIONS = new String[]{"6x6", "9x9"};
    private final String[] DIFFICULTY_OPTIONS = new String[]{"Easy", "Medium", "Hard"};

    private Spinner gridSize;
    private Spinner difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_start);

        gridSize = findViewById(R.id.gridSizeSelect);
        ArrayAdapter<String> gridOptionAdaptor = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, GRID_OPTIONS);
        gridSize.setAdapter(gridOptionAdaptor);

        difficulty = findViewById(R.id.difficultySelect);
        ArrayAdapter<String> difficultyOptionAdaptor = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, DIFFICULTY_OPTIONS);
        difficulty.setAdapter(difficultyOptionAdaptor);

        addListenerStart();
        addListenerScore();
        addListenerResume();
    }

    void addListenerStart() {
        findViewById(R.id.startButton).setOnClickListener(v -> {
            Intent intent = new Intent(this, SudokuActivity.class);
            intent.putExtra("gridSize", gridSize.getSelectedItem().toString());
            intent.putExtra("difficulty", difficulty.getSelectedItem().toString());
            intent.putExtra("username", getIntent().getStringExtra("username"));
            intent.putExtra("resume", false);
            startActivity(intent);
        });
    }

    void addListenerResume() {
        findViewById(R.id.resume_button).setOnClickListener(v -> {
            Intent intent = new Intent(this, SudokuActivity.class);
            intent.putExtra("gridSize", gridSize.getSelectedItem().toString());
            intent.putExtra("difficulty", difficulty.getSelectedItem().toString());
            intent.putExtra("username", getIntent().getStringExtra("username"));
            intent.putExtra("resume", true);
            startActivity(intent);
        });
    }

    void addListenerScore() {
        findViewById(R.id.playerScoreButton).setOnClickListener(v -> {
            Intent intent = new Intent(this, ScoreboardActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            intent.putExtra("currGame", "sudoku");
            startActivity(intent);
        });
    }
}
