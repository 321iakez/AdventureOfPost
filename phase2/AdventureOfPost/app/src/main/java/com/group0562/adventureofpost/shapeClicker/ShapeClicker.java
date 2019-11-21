package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.group0562.adventureofpost.Puzzles;
import com.group0562.adventureofpost.shapeClicker.ui.SCEndResultView;


public class ShapeClicker extends Puzzles {
    /* bound indexes follow from left, right, up and down */
    static double[] bound;

    /**
     * the current shape that is being displayed to player to click
     */
    private Shape s_object;

    /**
     * the paint used for the shape being clicked
     */
    private Paint paint;

    /**
     * string recording the type of shape being tapped
     */
    private static String shape;

    /**
     * boolean tracking whether the tapped location is within the shape
     */
    private boolean within = false;

    /**
     * boolean to track if the type of shape is changed
     */
    private static boolean changed;

    /**
     * constructor for this ShapeClicker
     *
     * @param time the time limit for this ShapeClicker
     * @param p    the paint for the shapes
     */
    ShapeClicker(long time, Paint p) {
        super(new ShapeClickerStats(time));
        this.paint = p;
        s_object = new Circle(50, 50, this.paint);
        s_object.setLocation();
    }

    /**
     * set the boundary for this ShapeClicker
     */
    static void setBound(double[] bound) {
        ShapeClicker.bound = bound;
    }

    /**
     * check if the location tapped is within the shape
     *
     * @param cursor_x x coordinate of the location tapped
     * @param cursor_y y coordinate of the location tapped
     */
    void checkWithinBall(double cursor_x, double cursor_y) {
        this.within = s_object.checkWithin(cursor_x, cursor_y);
        update();
        checkComplete();
    }

    /**
     * Draw the shape for this ShapeClicker
     */
    void draw(Canvas canvas) {
        checkChangedObject();
        s_object.draw(canvas);
    }

    /**
     * Change the type of shape of the ShapeClicker
     */
    public static void setShape(String type_of_shape) {
        ShapeClicker.shape = type_of_shape;
        ShapeClicker.changed = true;
    }

    /**
     * construct the new shape after changing the type of shape. Set this.changed to false for further changes.
     */
    private void checkChangedObject() {
        if (ShapeClicker.changed) {
            if (ShapeClicker.shape.equals("Circle")) {
                s_object = new Circle(s_object.getCoordinate_x(), s_object.getCoordinate_y(), this.paint);
            } else if (ShapeClicker.shape.equals("Square")) {
                s_object = new Square(s_object.getCoordinate_x(), s_object.getCoordinate_y(), this.paint);
            } else {
                s_object = new Triangle(s_object.getCoordinate_x(), s_object.getCoordinate_y(), this.paint);
            }
        }
        ShapeClicker.changed = false;
    }

    /**
     * update the ShapeClicker during the player plays the puzzle
     */
    @Override
    public void update() {
        super.update();
        if (this.within) {
            this.puzzleStats.setPoints(1);
            notifyObservers();
            s_object.setLocation();
        } else {
            this.puzzleStats.setLives(1);
            s_object.setLocation();
        }
    }

    /**
     * check if the puzzle is completed, or the player used up the lives
     */
    @Override
    public void checkComplete() {
        if (this.puzzleStats.getPoints() == 70) {
            SCEndResultView.setBeat_the_game(true);
            this.setPuzzleComplete(true);
        }
        if (this.puzzleStats.getPoints() >= 70) {
            this.setPuzzleComplete(true);
        }
        if (this.puzzleStats.getLives() < 1) {
            this.setPuzzleComplete(false);
            SCEndResultView.setBeat_the_game(false);
        }
    }
}
