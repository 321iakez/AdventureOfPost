package com.group0562.adventureofpost.sudoku;

import com.group0562.adventureofpost.model.PuzzleStats;

import java.util.Observable;

public class SudokuStats extends PuzzleStats {
    SudokuStats(long time) {
        super(time);
    }

    @Override
    public void updatePoints() {

    }

    @Override
    public void update(Observable o, Object arg) {
        updateTime();
        updatePoints();
    }
}
