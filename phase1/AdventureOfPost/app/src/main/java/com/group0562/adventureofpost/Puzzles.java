package com.group0562.adventureofpost;

import com.group0562.adventureofpost.model.PuzzleStats;

public abstract class Puzzles {

    public PuzzleStats puzzleStats;
    private long startTime;
    private boolean puzzleComplete = false;

    public Puzzles(long time) {
        puzzleStats = new PuzzleStats(time);

        startTime = System.currentTimeMillis();
    }

    public void updateTime() {
        long currTime = System.currentTimeMillis();
        puzzleStats.elapseTime(currTime - startTime);
        startTime = currTime;
    }

    // TODO: add pause and resume

    public abstract void updatePoints();

    public abstract void checkComplete();

    public void update() {
        updateTime();
        updatePoints();
        checkComplete();

        if (puzzleStats.getTime() == 0 | puzzleComplete) {
            onStop();
        }
    }

    public void onStop() {
        if (!puzzleComplete) {
            puzzleStats.setLives(0);
        }

        // TODO: display live points in phase 2
    }

    public void setPuzzleComplete(boolean b){
        this.puzzleComplete = b;
    }
}
