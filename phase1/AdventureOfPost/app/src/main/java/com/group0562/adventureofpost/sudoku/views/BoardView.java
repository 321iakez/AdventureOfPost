package com.group0562.adventureofpost.sudoku.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
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
    }
}
