package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.group0562.adventureofpost.AdventureOfPost;
import com.group0562.adventureofpost.Puzzles;

import java.util.Observable;

public abstract class ShapeClicker extends Observable {
    public AdventureOfPost.PuzzleStats puzzleStats;
    private boolean puzzleComplete = false;

    ShapeClicker(AdventureOfPost.PuzzleStats statsInst) {
            puzzleStats = statsInst;
    }
    abstract void draw(Canvas canvas);
    abstract void checkWithinBall(double cursor_x, double cursor_y);
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
