package com.group0562.adventureofpost.circleClicker;

import com.group0562.adventureofpost.model.PuzzleStats;

import java.util.Observable;

public class CircleClickerStats extends PuzzleStats {
    public CircleClickerStats(long time) {
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
