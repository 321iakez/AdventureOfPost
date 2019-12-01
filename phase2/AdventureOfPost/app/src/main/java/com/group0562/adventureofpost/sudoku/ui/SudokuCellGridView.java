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


/**
 * GridView for displaying Sudoku game board.
 */
public class SudokuCellGridView extends GridView {

    private Button[][] gridCells;
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

    void setPresenter(SudokuPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    void createTileButtons(Context context) {
        int sideLength = presenter.getDim();

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

    private void onClickGridCell(View view) {
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

    /**
     * Load given value into UI cell at (row, col).
     *
     * @param row   the row number.
     * @param col   the column number.
     * @param value the value to be displayed.
     */
    void loadValues(int row, int col, String value) {
        gridCells[row][col].setText(value);
    }

    /**
     * Return a list of buttons on GridView for Adaptor to change layout.
     */
    ArrayList<Button> getTileButtons() {
        ArrayList<Button> result = new ArrayList<>();
        for (int row = 0; row != presenter.getDim(); row++) {
            for (int col = 0; col != presenter.getDim(); col++) {
                result.add(gridCells[row][col]);
            }
        }
        return result;
    }
}
