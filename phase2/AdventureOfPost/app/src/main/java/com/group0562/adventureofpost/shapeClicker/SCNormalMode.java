package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.group0562.adventureofpost.shapeClicker.ui.SCEndResultView;


public class SCNormalMode extends ShapeClicker {
    /* bound indexes follow from left, right, up and down */
    static double[] bound;

    /**
     * the current shape that is being displayed to player to click
     */
    Shape s_object;

    /**
     * the paint used for the shape being clicked
     */
    private Paint paint;

    private static Paint INITIAL_PAINT;

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

    private static long TIME_LIMIT;

    private long pausing_time;

    private long pausing_life;

    private long pausing_points;

    /**
     * constructor for this SCNormalMode
     *
     * @param time the time limit for this SCNormalMode
     * @param p    the paint for the shapes
     */
    SCNormalMode(long time, Paint p) {
        super(new ShapeClickerStats(time, SCSetting.getUsername()));
        TIME_LIMIT = time;
        this.paint = p;
        INITIAL_PAINT = p;
        s_object = new Circle(50, 50, this.paint);
        setShape(SCSetting.getShape());
        checkObject();
        setDifficulty(SCSetting.getDifficulty());
    }

    /**
     * set the boundary for this SCNormalMode
     */
    static void setBound(double[] bound) {
        SCNormalMode.bound = bound;
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
     * Draw the shape for this SCNormalMode
     */
    void draw(Canvas canvas) {
        s_object.draw(canvas);
    }

    /**
     * Change the type of shape of the SCNormalMode
     */
    void setDifficulty(String difficulty) {
        s_object.setRadius(difficulty);
    }

    /**
     * Change the type of shape of the SCNormalMode
     */
    public void setShape(String shape) {
        this.shape = shape;
        this.changed = true;
    }

    /**
     * construct the new shape after changing the type of shape. Set this.changed to false for further changes.
     */
    private void checkObject() {
        if (this.changed) {
            if (this.shape.equals("Circle")) {
                s_object = new Circle(s_object.getCoordinate_x(), s_object.getCoordinate_y(), this.paint);
            } else if (this.shape.equals("Square")) {
                s_object = new Square(s_object.getCoordinate_x(), s_object.getCoordinate_y(), this.paint);
            } else {
                s_object = new Triangle(s_object.getCoordinate_x(), s_object.getCoordinate_y(), this.paint);
            }
            s_object.setRadius(SCSetting.getDifficulty());
        }
        this.changed = false;
    }

    /**
     * update the SCNormalMode during the player plays the puzzle
     */
    @Override
    public void update() {
        super.update();
        if (this.within) {
            this.puzzleStats.setPoints(1);
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
        if (this.puzzleStats.getPoints() == 50) {
            SCEndResultView.setBeat_the_game(true);
            this.setPuzzleComplete(true);
        }
        if (this.puzzleStats.getPoints() >= 50) {
            this.setPuzzleComplete(true);
        }
        if (this.puzzleStats.getLives() < 1) {
            this.setPuzzleComplete(false);
            SCEndResultView.setBeat_the_game(false);
        }
    }
    public static void reset(){
        SCNormalMode sc = new SCNormalMode(TIME_LIMIT, INITIAL_PAINT);
        sc.resetGame();
    }
    public void resetGame(){
        this.puzzleStats.setTime(TIME_LIMIT);
        this.puzzleStats.setLives(10);
        this.puzzleStats.setPoints(0);
    }

    public void pause(){
        pausing_life = puzzleStats.getLives();
        pausing_points = puzzleStats.getPoints();
        pausing_time = (long)puzzleStats.getTime();

    }
}
