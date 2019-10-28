package com.group0562.adventureofpost.puzzleTwo;

import com.group0562.adventureofpost.model.PuzzleStats;

import java.util.Observable;

public class PuzzleTwoStats extends PuzzleStats {
    public PuzzleTwoStats(long time) {
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
