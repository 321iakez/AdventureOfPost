package com.group0562.adventureofpost.sudoku.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;

public class BoardView extends GridLayout {

    private final int BOARD_SIZE = 6;
    private Button[][] cells = new Button[BOARD_SIZE][BOARD_SIZE];

    public BoardView(Context context) {
        super(context);
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initBoardView(Context context) {
        setColumnCount(BOARD_SIZE);

        // Set overall grid layout
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -1);
        params.setMargins(1, 1, 1, 1);

        // Put tiles to the grid and assign each tile to a button
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Button button = new Button(getContext());
            }
        }
    }
}
