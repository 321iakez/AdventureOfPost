package com.group0562.adventureofpost.sudoku.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.R;

public class SudokuStartActivity extends AppCompatActivity {

    private final String[] GRID_OPTIONS = new String[]{"6x6", "9x9"};
    private final String[] DIFFICULTY_OPTIONS = new String[]{"Easy", "Medium", "Hard"};
    private final Integer[] LEVEL_OPTIONS = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private Spinner gridSize;
    private Spinner difficulty;
    private Spinner level;

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

        level = findViewById(R.id.levelSelect);
        ArrayAdapter<Integer> levelOptionAdaptor = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, LEVEL_OPTIONS);
        level.setAdapter(levelOptionAdaptor);

        findViewById(R.id.startButton).setOnClickListener(this::onClickStart);
    }

    void onClickStart(View view) {
        Intent intent = new Intent(this, SudokuActivity.class);
        intent.putExtra("gridSize", gridSize.getSelectedItem().toString());
        intent.putExtra("difficulty", difficulty.getSelectedItem().toString());
        intent.putExtra("level", (Integer) level.getSelectedItem());
        startActivity(intent);
    }
}
