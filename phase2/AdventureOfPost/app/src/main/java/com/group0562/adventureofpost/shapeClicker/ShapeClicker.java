package com.group0562.adventureofpost.shapeClicker;

import android.content.Context;
import android.graphics.Canvas;

import com.group0562.adventureofpost.database.DatabaseHelper;


public abstract class ShapeClicker {
    private ShapeClickerStats puzzleStats;
    private boolean puzzleComplete = false;

    static double[] bound;


    ShapeClicker(ShapeClickerStats statsInst) {
        puzzleStats = statsInst;
    }

    public abstract void draw(Canvas canvas);

    public abstract void checkWithinBall(double cursor_x, double cursor_y);

    public abstract void checkComplete();

    public void update() {
        checkComplete();

        if (puzzleStats.getTime() == 0 | puzzleComplete) {
            onStop();
        }
    }

    private void onStop() {
        if (!puzzleComplete) {
            puzzleStats.setLives(0);
        }
    }

    public static void setBound(double[] bound) {
        ShapeClicker.bound = bound;
    }

    void setPuzzleComplete(boolean b) {
        this.puzzleComplete = b;
    }

    public boolean getPuzzleComplete() {
        return this.puzzleComplete;
    }

    public void saveStats(Context context) {
        puzzleStats.saveStats(context);
    }

    public ShapeClickerStats getPuzzleStats() {
        return puzzleStats;
    }

    public void saveShapeClicker(Context context) {
        DatabaseHelper db = new DatabaseHelper(context);
        db.insertGameState("shapeClicker", SCSetting.getUsername(), puzzleStats.getSCData());
    }
}
