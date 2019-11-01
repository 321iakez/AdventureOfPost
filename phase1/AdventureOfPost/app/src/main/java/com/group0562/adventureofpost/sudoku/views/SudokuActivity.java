package com.group0562.adventureofpost.sudoku.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.sudoku.Sudoku;

public class SudokuActivity extends AppCompatActivity implements SudokuCellFragment.OnFragmentInteractionListener {

    private int currentRow = 0;
    private int currentCol = 0;
    private TextView hintsLeft;
    private TextView timerText;
    private Sudoku presenter;

    private SudokuCellFragment cellGroupFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        presenter = new Sudoku(getResources().openRawResource(R.raw.sudoku));
        cellGroupFrag = (SudokuCellFragment) getSupportFragmentManager().findFragmentById(R.id.boardFragment);

        // Display initial board values
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                int cellValue = presenter.getGameBoard().getCell(row, col).getValue();
                if (cellValue != 0) {
                    cellGroupFrag.loadValues(cellValue, row, col);
                }
            }
        }
    }

    public void onClickNumpad(View view) {
        Button numClicked = (Button) view;
        int newValue = Integer.parseInt(numClicked.getTag().toString());

        // Update backend board
        boolean updateSuccess = presenter.getGameBoard().insertNum(currentRow, currentCol, newValue);

        // Load value on board
        if (updateSuccess) {
            cellGroupFrag.loadValues(newValue, currentRow, currentCol);
        }
    }

    @Override
    public void onFragmentInteraction(int row, int col) {
        if (presenter.getGameBoard().getCell(row, col).isLocked()) {
            Toast.makeText(getApplicationContext(), "Can not change start piece", Toast.LENGTH_SHORT).show();
        } else {
            currentRow = row;
            currentCol = col;
        }
    }
}
