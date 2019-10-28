package com.group0562.adventureofpost.puzzleThree;

import com.group0562.adventureofpost.model.PuzzleStats;

import java.util.Observable;

public class PuzzleThreeStats extends PuzzleStats {
    public PuzzleThreeStats(long time) {
        super(time);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateTime();
        updatePoints();
    }

    @Override
    public void updatePoints() {
        setPoints(1);
    }
}
