package com.group0562.adventureofpost.circleClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.group0562.adventureofpost.Puzzles;


public class CircleClicker extends Puzzles {
    /* bound indexes follow from left, right, up and down */
    static double[] bound;
    private Circle circle;
    private Paint paint;
    private double center_x;
    private double center_y;
    private double r;
    private boolean within = false;

    public CircleClicker(long time, float radius, Paint p) {

        super(new CircleClickerStats(time));
        this.paint = p;
        circle = new Circle(50, 50, radius, this.paint);
        circle.setBallLocation();
    }

    public static void setBound(double[] bound) {
        CircleClicker.bound = bound;
    }

    // call this before update in front end
    void checkWithinBall(double cursor_x, double cursor_y) {
        this.within = circle.checkWithinBall(cursor_x, cursor_y);
        if(this.within){
            update();
            checkComplete();
        }
    }

    void draw(Canvas canvas){
        circle.draw(canvas);
    }

    @Override
    public void update() {
        super.update();
        if (this.within) {
            notifyObservers();
            circle.setBallLocation();
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
