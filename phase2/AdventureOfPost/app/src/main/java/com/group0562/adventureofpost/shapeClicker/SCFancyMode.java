package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.group0562.adventureofpost.shapeClicker.ui.SCEndResultView;

import java.util.ArrayList;

public class SCFancyMode extends ShapeClicker {
    /* bound indexes follow from left, right, up and down */
    static double[] bound;

    /**
     * the current shape that is being displayed to player to click
     */
    private ArrayList<Shape> s_object;

    /**
     * the current combo number, not available in normal mode
     */
    private long combo;

    private int to_erase_object;

    /**
     * the paint used for the shape being clicked
     */
    private Paint paint;

    private static Paint TEXT_PAINT;

    private static Paint INITIAL_PAINT;

    /**
     * string recording the type of shape being tapped
     */
    private static String shape;

    private boolean within;

    private int clicked_score;

    private static long TIME_LIMIT;

    /**
     * constructor for this SCNormalMode
     *
     * @param time the time limit for this SCNormalMode
     * @param p    the paint for the shapes
     */
    SCFancyMode(long time, Paint p) {
        super(new ShapeClickerStats(time, SCSetting.getUsername()));
        TIME_LIMIT = time;
        this.paint = p;
        TEXT_PAINT = new Paint();
        TEXT_PAINT.setColor(Color.BLACK);
        TEXT_PAINT.setStrokeWidth(3);
        TEXT_PAINT.setTextSize(50);
        INITIAL_PAINT = p;
        ShapeBuilder builder = new ShapeBuilder(50, 50, this.paint);
        this.s_object = builder.getS_objects();
        //for(Shape item: this.s_object){item.setLocation();}
        setDifficulty(SCSetting.getDifficulty());
    }

    /**
     * set the boundary for this SCNormalMode
     */
    static void setBound(double[] bound) {
        SCFancyMode.bound = bound;
    }

    /**
     * check if the location tapped is within the shape
     *
     * @param cursor_x x coordinate of the location tapped
     * @param cursor_y y coordinate of the location tapped
     */
    void checkWithinBall(double cursor_x, double cursor_y) {
        this.within = false;
        for (int i = 0; i<this.s_object.size(); i++) {
            if (this.s_object.get(i).checkWithin(cursor_x, cursor_y)) {
                this.within = true;
                this.clicked_score = checkShape(this.s_object.get(i));
                this.to_erase_object = i;
                this.combo += 1;
            }
        }
        if (!this.within)
            this.combo = 0;
        update();
        checkComplete();
    }

    /**
     * Draw the shape for this SCNormalMode
     */
    public void draw(Canvas canvas) {
        for (Shape item: s_object) {
            item.draw(canvas);
        }
        String combo_text = Long.toString(this.getCombos());
        combo_text = combo_text + " Combos";
        canvas.drawText(combo_text, 800, 1400, TEXT_PAINT);
    }

    /**
     * Change the type of shape of the SCNormalMode
     */
    void setDifficulty(String difficulty) {
        for(Shape item: s_object) {
            item.setRadius(difficulty);
        }
    }

    int checkShape(Shape shape){
        if (shape instanceof Circle){
            return 1;
        }
        else if (shape instanceof Square){
            return 2;
        }
        else {
            return 3;
        }
    }

    /**
     * update the SCNormalMode during the player plays the puzzle
     */

    public void update() {
        super.update();
        if (this.within) {
            this.puzzleStats.setPoints(clicked_score);
            s_object.remove(this.to_erase_object);
        }
        else {
            this.puzzleStats.setLives(1);
        }
    }

    /**
     * check if the puzzle is completed, or the player used up the lives
     */
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

    public long getCombos(){
        return this.combo;
    }
}
