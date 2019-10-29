package com.group0562.adventureofpost.Trivia;

import com.group0562.adventureofpost.model.PuzzleStats;

import java.util.Observable;

public class TriviaStats extends PuzzleStats {
    public TriviaStats(long time) {
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
