package com.group0562.adventureofpost.sudoku;

import com.group0562.adventureofpost.AdventureOfPost;

import java.util.Observable;

public class SudokuStats extends AdventureOfPost.PuzzleStats {
    SudokuStats(long time) {
        super(time);
    }

    @Override
    public void updatePoints() {

    }

    @Override
    public void updateTime(){
        long currTime = System.currentTimeMillis();
        this.setTime(currTime - getStartTime());
    }

    @Override
    public void update(Observable o, Object arg) {
        updateTime();
        updatePoints();
    }
}
