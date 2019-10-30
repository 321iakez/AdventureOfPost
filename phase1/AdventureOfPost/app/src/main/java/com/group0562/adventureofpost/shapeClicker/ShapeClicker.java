package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.group0562.adventureofpost.Puzzles;


public class ShapeClicker extends Puzzles {
    /* bound indexes follow from left, right, up and down */
    static double[] bound;
    private Shape s_object;
    private Paint paint;
    private static String shape;
    private boolean within = false;

    public ShapeClicker(long time, float radius, Paint p) {
        super(new ShapeClickerStats(time));
        this.paint = p;
        s_object = new Circle(50, 50, radius, this.paint);
        s_object.setLocation();
    }

    public static void setBound(double[] bound) {
        ShapeClicker.bound = bound;
    }

    // call this before update in front end
    void checkWithinBall(double cursor_x, double cursor_y) {
        this.within = s_object.checkWithin(cursor_x, cursor_y);
        if(this.within){
            update();
            checkComplete();
        }
    }

    void draw(Canvas canvas){
        s_object.draw(canvas);
    }

    public static void setShape(String type_of_shape){ShapeClicker.shape = type_of_shape;}

    @Override
    public void update() {
        super.update();
        if (this.within) {
            notifyObservers();
            s_object.setLocation();
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
