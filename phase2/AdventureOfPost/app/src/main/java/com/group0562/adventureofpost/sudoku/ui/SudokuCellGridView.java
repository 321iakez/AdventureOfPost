package com.group0562.adventureofpost.sudoku.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.group0562.adventureofpost.R;

import java.util.ArrayList;

public class SudokuCellGridView extends GridView {

    private ArrayList<Button> gridCells;
    private SudokuActivity gameActivity;

    /**
     * Construct gridView for Sudoku game board
     *
     * @param context the sudoku game activity
     */
    public SudokuCellGridView(Context context) {
        super(context);
    }

    /**
     * construct gridView of gesture detect
     *
     * @param context game activity
     * @param attrs   attribute
     */
    public SudokuCellGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setGridCells(ArrayList<Button> gridCells) {
        this.gridCells = gridCells;
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    public void createTileButtons(Context context, int width, int height) {
//        gameActivity = (SudokuActivity) context;

        gridCells = new ArrayList<>();
        for (int row = 0; row != width; row++) {
            for (int col = 0; col != height; col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(R.drawable.table_border_cell);
                tmp.setOnClickListener(v -> v.setBackgroundResource(R.drawable.table_selected_cell));
                this.gridCells.add(tmp);
            }
        }
    }

    void loadValues(int row, int col, int value) {

    }

    public ArrayList<Button> getTileButtons() {
        return gridCells;
    }
}
