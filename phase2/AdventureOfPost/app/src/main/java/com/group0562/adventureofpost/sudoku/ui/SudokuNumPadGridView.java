package com.group0562.adventureofpost.sudoku.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.group0562.adventureofpost.sudoku.SudokuPresenter;

import java.util.ArrayList;
import java.util.Arrays;

public class SudokuNumPadGridView extends GridView {

    private SudokuPresenter presenter;
    private SudokuCellGridView gridView;

    private Button[] numPad;

    public SudokuNumPadGridView(Context context) {
        super(context);
    }

    public SudokuNumPadGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param gridView the context
     */
    public void createTileButtons(Context context, SudokuCellGridView gridView) {
        this.gridView = gridView;

        numPad = new Button[presenter.getDim()];
        for (int num = 0; num != presenter.getDim(); num++) {
            Button cell = new Button(context);
            cell.setTag(String.valueOf(num + 1));
            cell.setText(String.valueOf(num + 1));
            cell.setOnClickListener(this::onClickNumpad);
            numPad[num] = cell;
        }
    }

    void setPresenter(SudokuPresenter presenter) {
        this.presenter = presenter;
    }

    void onClickNumpad(View view) {
        if (presenter.getCurrCol() == -1 || presenter.getCurrRow() == -1) {
            Toast.makeText(getContext(), "No cell selected!", Toast.LENGTH_SHORT).show();
            return;
        }

        Button numClicked = (Button) view;
        int newValue = Integer.parseInt(numClicked.getTag().toString());

        // Update backend board
        boolean updateSuccess = presenter.addNum(newValue);

        // Load value on board
        if (updateSuccess) {
//            gridView.loadValues(presenter.getCurrRow(), presenter.getCurrCol(), newValue);
            presenter.addMoves();
        } else {
            Toast.makeText(getContext(), "Conflict detected!", Toast.LENGTH_SHORT).show();
            presenter.addConflicts();
        }

        // Update
        presenter.update();
    }

    public ArrayList<Button> getTileButtons() {
        return new ArrayList<>(Arrays.asList(numPad));
    }
}
