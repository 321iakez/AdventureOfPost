package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.group0562.adventureofpost.shapeClicker.ui.SCEndResultView;

import java.util.ArrayList;

public class SCFancyMode extends ShapeClicker {

    /**
     * the current shape that is being displayed to player to click
     */
    private ArrayList<Shape> s_object;

    /**
     * the current combo number, not available in normal mode
     */
    private long combo;

    private int to_erase_object;

    private static Paint textPaint;

    private boolean within;

    private int clicked_score;

    /**
     * constructor for this SCNormalMode
     *
     * @param time the time limit for this SCNormalMode
     * @param p    the paint for the shapes
     */
    public SCFancyMode(long time, Paint p) {
        super(new ShapeClickerStats(time, SCSetting.getUsername()));
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStrokeWidth(3);
        textPaint.setTextSize(50);
        ShapeBuilder builder = new ShapeBuilder(50, 50, p);
        s_object = builder.getS_objects();
        setDifficulty(SCSetting.getDifficulty());
    }

    /**
     * Change the type of shape of the SCNormalMode
     */
    void setDifficulty(String difficulty) {
        for (Shape item : s_object) {
            item.setRadius(difficulty);
        }
    }

    /**
     * check if the location tapped is within the shape
     *
     * @param cursor_x x coordinate of the location tapped
     * @param cursor_y y coordinate of the location tapped
     */
    @Override
    public void checkWithinBall(double cursor_x, double cursor_y) {
        within = false;
        for (int i = 0; i < this.s_object.size(); i++) {
            if (s_object.get(i).checkWithin(cursor_x, cursor_y)) {
                within = true;
                clicked_score = checkShape(s_object.get(i));
                to_erase_object = i;
                combo += 1;
            }
        }
        if (!within)
            combo = 0;

        update();
        checkComplete();
    }

    /**
     * Draw the shape for this SCNormalMode
     */
    @Override
    public void draw(Canvas canvas) {
        for (Shape item : s_object) {
            item.draw(canvas);
        }
        String combo_text = Long.toString(getCombos());
        combo_text = combo_text + " Combos";
        canvas.drawText(combo_text, 800, 40, textPaint);
    }

    private int checkShape(Shape shape) {
        if (shape instanceof Circle) {
            return 1;
        } else if (shape instanceof Square) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * update the SCNormalMode during the player plays the puzzle
     */
    @Override
    public void update() {
        super.update();
        if (within) {
            getPuzzleStats().setPoints(clicked_score);
            s_object.remove(to_erase_object);
        } else {
            getPuzzleStats().setLives(1);
        }
    }

    /**
     * check if the puzzle is completed, or the player used up the lives
     */
    @Override
    public void checkComplete() {
        if (getPuzzleStats().getPoints() == 70) {
            SCEndResultView.setBeat_the_game(true);
            setPuzzleComplete(true);
        }
        if (getPuzzleStats().getPoints() >= 70) {
            setPuzzleComplete(true);
        }
        if (getPuzzleStats().getLives() < 1) {
            setPuzzleComplete(false);
            SCEndResultView.setBeat_the_game(false);
        }
    }

    private long getCombos() {
        return this.combo;
    }
}
