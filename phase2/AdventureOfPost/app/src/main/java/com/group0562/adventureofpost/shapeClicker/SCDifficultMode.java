package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.group0562.adventureofpost.Puzzles;
import com.group0562.adventureofpost.shapeClicker.ui.SCEndResultView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SCDifficultMode extends Puzzles {
    /* bound indexes follow from left, right, up and down */
    static double[] bound;

    /**
     * the current shape that is being displayed to player to click
     */
    private ArrayList<Shape> s_object;

    private int to_erase_object;

    /**
     * the paint used for the shape being clicked
     */
    private Paint paint;

    private static Paint INITIAL_PAINT;

    /**
     * string recording the type of shape being tapped
     */
    private static String shape;

    private boolean within;

    private static long TIME_LIMIT;

    /**
     * constructor for this ShapeClicker
     *
     * @param time the time limit for this ShapeClicker
     * @param p    the paint for the shapes
     */
    SCDifficultMode(long time, Paint p) {
        super(new ShapeClickerStats(time));
        TIME_LIMIT = time;
        this.paint = p;
        INITIAL_PAINT = p;
        ShapeBuilder builder = new ShapeBuilder(100, 15);
        this.s_object = builder.getS_objects();
        for(Shape item: this.s_object){item.setLocation();}
    }

    /**
     * set the boundary for this ShapeClicker
     */
    static void setBound(double[] bound) {
        SCDifficultMode.bound = bound;
    }

    /**
     * check if the location tapped is within the shape
     *
     * @param cursor_x x coordinate of the location tapped
     * @param cursor_y y coordinate of the location tapped
     */
    void checkWithinBall(double cursor_x, double cursor_y) {
        // TODO: this.within = s_object.checkWithin(cursor_x, cursor_y);
        this.within = false;
        for (int i = 0; i<this.s_object.size(); i++){
            if (this.s_object.get(i).checkWithin(cursor_x, cursor_y)){
                this.within = true;
                this.to_erase_object = i;
            }
        }
        update();
        checkComplete();
    }

    /**
     * Draw the shape for this ShapeClicker
     */
    void draw(Canvas canvas) {
        //checkChangedObject();
        for (Shape item: s_object) {
            item.draw(canvas);
        }
    }

    /**
     * Change the type of shape of the ShapeClicker
     *
    public static void setShape(String type_of_shape) {
        ShapeClicker.shape = type_of_shape;
        ShapeClicker.changed = true;
    }*/

    /**
     * construct the new shape after changing the type of shape. Set this.changed to false for further changes.
     */
    /*private void checkChangedObject() {
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
            s_object.remove(this.to_erase_object);
        }
        else {
            this.puzzleStats.setLives(1);
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
    public static void reset(){
        ShapeClicker sc = new ShapeClicker(TIME_LIMIT, INITIAL_PAINT);
        sc.resetgame();
    }
    public void resetgame(){
        this.puzzleStats.setTime(TIME_LIMIT);
        this.puzzleStats.setLives(10);
        this.puzzleStats.setPoints(0);
    }
}
