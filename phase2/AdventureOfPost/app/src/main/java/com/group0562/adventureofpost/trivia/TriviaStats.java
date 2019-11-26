package com.group0562.adventureofpost.trivia;

import com.group0562.adventureofpost.model.PuzzleStats;

import java.io.Serializable;
import java.util.Observable;

public class TriviaStats extends PuzzleStats implements Serializable {
    TriviaStats(long time) {
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
