package com.group0562.adventureofpost;

import java.io.Serializable;
import java.util.Observable;

public abstract class Puzzles extends Observable implements Serializable {

    public AdventureOfPost.PuzzleStats puzzleStats;
    private boolean puzzleComplete = false;

    public Puzzles(AdventureOfPost.PuzzleStats statsInst) {
        puzzleStats = statsInst;
    }

    //TODO make more generalized once PuzzleStats is deleted
    public Puzzles(){}


    // TODO: add pause and resume

    public abstract void checkComplete();

    public void update() {
        checkComplete();
        notifyObservers();

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

    public void setPuzzleComplete(boolean b) {
        this.puzzleComplete = b;
    }

    public boolean getPuzzleComplete() {
        return this.puzzleComplete;
    }
}
