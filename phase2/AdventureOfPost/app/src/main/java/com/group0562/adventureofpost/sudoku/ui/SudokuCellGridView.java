package com.group0562.adventureofpost.sudoku.ui;

import android.content.Context;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class SudokuCellGridView extends GridView {

    private ArrayList<Button> gridCells;

    /**
     * Construct gridView for Sudoku game board
     *
     * @param context the sudoku game activity
     */
    public SudokuCellGridView(Context context) {
        super(context);


    }

    public void setGridCells(ArrayList<Button> gridCells) {
        this.gridCells = gridCells;
    }
}
