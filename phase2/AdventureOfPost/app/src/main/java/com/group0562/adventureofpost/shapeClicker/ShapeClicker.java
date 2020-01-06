package com.group0562.adventureofpost.shapeClicker;

import android.content.Context;
import android.graphics.Canvas;

import com.group0562.adventureofpost.database.DatabaseHelper;

/**
 * this serves as the parent class of two child classes who are also a presenter, this class serves as a easier usage and cleaner design
 */
public abstract class ShapeClicker {
    private ShapeClickerStats puzzleStats;
    private boolean puzzleComplete = false;
    static double[] bound;

    /**
     * constructor for this class
     */
    ShapeClicker(ShapeClickerStats statsInst) {
        puzzleStats = statsInst;
    }

    /**
     * to update and see if the puzzle is complete
     */
    public void update() {
        checkComplete();

        if (puzzleStats.getTime() == 0 | puzzleComplete) {
            onStop();
        }
    }

    /**
     * to set the game as lost
     */
    private void onStop() {
        if (!puzzleComplete) {
            puzzleStats.setLives(0);
        }
    }

    /**
     * getters and setters for this class
     */
    public static void setBound(double[] bound) {
        ShapeClicker.bound = bound;
    }

    void setPuzzleComplete(boolean b) {
        this.puzzleComplete = b;
    }

    public ShapeClickerStats getPuzzleStats() {
        return puzzleStats;
    }

    /**
     * this method is for saving the gameState for a user so next time he/she gets to resume his/her game
     */
    public void saveShapeClicker(Context context) {
        DatabaseHelper db = new DatabaseHelper(context);
        db.insertGameState("shapeClicker", SCSetting.getUsername(), puzzleStats.getSCData());
    }

    /**
     * three abstract methods that its child needs to implement
     */
    public abstract void draw(Canvas canvas);

    public abstract void checkWithinBall(double cursor_x, double cursor_y);

    public abstract void checkComplete();
}
