package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.group0562.adventureofpost.shapeClicker.ui.SCEndResultView;

/**
 * this class is the presenter for one of the mode of the game shapeClicker
 */
public class SCNormalMode extends ShapeClicker {

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
    private String shape;

    /**
     * boolean tracking whether the tapped location is within the shape
     */
    private boolean within = false;

    /**
     * boolean to track if the type of shape is changed
     */
    private boolean changed;

    /**
     * constructor for this SCNormalMode
     *
     * @param time the time limit for this SCNormalMode
     * @param p    the paint for the shapes
     */
    public SCNormalMode(long time, Paint p) {
        super(new ShapeClickerStats(time));
        paint = p;
        s_object = new Circle(50, 50, paint);
        setShape(SCSetting.getShape());
        checkObject();
        setDifficulty(SCSetting.getDifficulty());
    }

    /**
     * Change the type of shape of the SCNormalMode
     */
    private void setDifficulty(String difficulty) {
        s_object.setRadius(difficulty);
    }

    /**
     * Change the type of shape of the SCNormalMode
     */
    private void setShape(String shape) {
        this.shape = shape;
        this.changed = true;
    }

    /**
     * check if the location tapped is within the shape
     *
     * @param cursor_x x coordinate of the location tapped
     * @param cursor_y y coordinate of the location tapped
     */
    @Override
    public void checkWithinBall(double cursor_x, double cursor_y) {
        within = s_object.checkWithin(cursor_x, cursor_y);
        update();
        checkComplete();
    }

    /**
     * Draw the shape for this SCNormalMode
     */
    @Override
    public void draw(Canvas canvas) {
        s_object.draw(canvas);
    }

    /**
     * construct the new shape after changing the type of shape. Set this.changed to false for further changes.
     */
    private void checkObject() {
        if (this.changed) {
            if (shape.equals("Circle")) {
                s_object = new Circle(s_object.getCoordinate_x(), s_object.getCoordinate_y(), paint);
            } else if (this.shape.equals("Square")) {
                s_object = new Square(s_object.getCoordinate_x(), s_object.getCoordinate_y(), paint);
            } else {
                s_object = new Triangle(s_object.getCoordinate_x(), s_object.getCoordinate_y(), paint);
            }
            s_object.setRadius(SCSetting.getDifficulty());
        }
        changed = false;
    }

    /**
     * update the SCNormalMode during the player plays the puzzle
     */
    @Override
    public void update() {
        super.update();
        if (within) {
            getPuzzleStats().setPoints(1);
            s_object.setLocation();
        } else {
            getPuzzleStats().setLives(1);
            s_object.setLocation();
        }
    }

    /**
     * check if the puzzle is completed, or the player used up the lives
     */
    @Override
    public void checkComplete() {
        if (getPuzzleStats().getPoints() == 50) {
            SCEndResultView.setBeat_the_game(true);
            setPuzzleComplete(true);
        }
        if (getPuzzleStats().getPoints() >= 50) {
            setPuzzleComplete(true);
        }
        if (getPuzzleStats().getLives() < 1) {
            setPuzzleComplete(false);
            SCEndResultView.setBeat_the_game(false);
        }
    }
}
