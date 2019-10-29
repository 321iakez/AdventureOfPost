package com.group0562.adventureofpost.circleClicker;

import android.graphics.Canvas;

import com.group0562.adventureofpost.Puzzles;


public class CircleClicker extends Puzzles {
    /* bound indexes follow from left, right, up and down */
    private static double[] bound;
    private double center_x;
    private double center_y;
    private double r;
    private boolean within = false;

    public CircleClicker(long time, double radius, double[] bounds) {

        super(new CircleClickerStats(time));
        this.r = radius;
        this.setBallLocation();
        CircleClicker.bound = bounds;
    }

    private void setBallLocation() {
        this.center_x = Math.random() * (CircleClicker.bound[1] - 2 * this.r) + this.r;
        this.center_y = Math.random() * (CircleClicker.bound[3] - 2 * this.r) + this.r;
    }

    // call this before update in front end
    void checkWithinBall(int cursor_x, int cursor_y) {
        if (Math.sqrt(Math.pow(this.center_x - cursor_x, 2) + Math.pow(this.center_y - cursor_y, 2)) <= r) {
            this.within = true;
        }
    }

    public void draw(Canvas canvas){

    }

    @Override
    public void update() {
        super.update();

        if (this.within) {
            notifyObservers();
            this.setBallLocation();
        }
    }

    @Override
    public void checkComplete() {
        if (this.puzzleStats.getPoints() >= 50) {
            this.setPuzzleComplete(true);
            this.puzzleStats.setPoints((int) this.puzzleStats.getTime());
        }
    }
}
