package com.group0562.adventureofpost.shapeClicker;

import com.group0562.adventureofpost.model.PuzzleStats;

import java.util.Observable;

public class ShapeClickerStats extends PuzzleStats {
    public ShapeClickerStats(long time) {
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
