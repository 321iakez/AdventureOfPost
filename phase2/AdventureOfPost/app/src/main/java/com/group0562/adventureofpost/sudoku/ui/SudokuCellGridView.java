package com.group0562.adventureofpost.sudoku.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.sudoku.SudokuPresenter;

import java.util.ArrayList;

public class SudokuCellGridView extends GridView {

    private Button[][] gridCells;
    private int sideLength;
    private SudokuPresenter presenter;

    /**
     * Construct gridView for Sudoku game board
     *
     * @param context the sudoku6_easy game activity
     */
    public SudokuCellGridView(Context context) {
        super(context);
    }

    /**
     * Construct gridView with attributes
     *
     * @param context game activity
     * @param attrs   attribute
     */
    public SudokuCellGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    public void createTileButtons(SudokuPresenter presenter, Context context, int sideLength) {
        this.presenter = presenter;
        this.sideLength = sideLength;

        gridCells = new Button[sideLength][sideLength];
        for (int row = 0; row != sideLength; row++) {
            for (int col = 0; col != sideLength; col++) {
                Button cell = new Button(context);
                cell.setBackgroundResource(R.drawable.table_border_cell);
                cell.setTag(String.valueOf(row) + col);
                cell.setOnClickListener(this::onClickGridCell);
                gridCells[row][col] = cell;
            }
        }
    }

    /**
     * Load given value into UI cell at (row, col).
     *
     * @param row   the row number.
     * @param col   the column number.
     * @param value the value to be displayed.
     */
    void loadValues(int row, int col, int value) {
        gridCells[row][col].setText(String.valueOf(value));
    }

    /**
     * Remove value for UI cell at (row, col).
     *
     * @param row the row number.
     * @param col the column number.
     */
    void removeValue(int row, int col) {
        gridCells[row][col].setText("");
    }

    void onClickGridCell(View view) {
        int row = Character.getNumericValue(view.getTag().toString().charAt(0));
        int col = Character.getNumericValue(view.getTag().toString().charAt(1));

        if (presenter.getCellLocked(row, col)) {
            Toast.makeText(getContext(), "Can not change start piece", Toast.LENGTH_SHORT).show();
        } else {
            if (presenter.getCurrCol() != -1 && presenter.getCurrRow() != -1) {
                gridCells[presenter.getCurrRow()][presenter.getCurrCol()].setBackgroundResource(R.drawable.table_border_cell);
            }
            view.setBackgroundResource(R.drawable.table_selected_cell);
            presenter.setCurrRow(row);
            presenter.setCurrCol(col);
        }
    }

    public ArrayList<Button> getTileButtons() {
        ArrayList<Button> result = new ArrayList<>();
        for (int row = 0; row != sideLength; row++) {
            for (int col = 0; col != sideLength; col++) {
                result.add(gridCells[row][col]);
            }
        }
        return result;
    }
}
