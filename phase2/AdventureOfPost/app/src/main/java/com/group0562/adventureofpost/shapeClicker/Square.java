package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import static java.lang.Math.*;


public class Square extends Shape {
    private Paint paint;
    static final String message = "Square";

    /**
     * Constructor of a Square, including setting the coordinates of its center
     */
    Square(double x, double y, Paint p) {
        super(x, y);
        this.paint = p;

    }

    /**
     * randomly reset the center of square
     */
    @Override
    public void setLocation() {
        this.coordinate_x = random() * (SCNormalMode.bound[1] - 2 * this.radius) + this.radius;
        this.coordinate_y = random() * (SCNormalMode.bound[3] - 2 * this.radius) + this.radius;
    }

    /**
     * @param cursor_x the x coordinate of the position player tap on.
     * @param cursor_y the y coordinate of the position player tap on.
     * @return a boolean whether the player taps on the square.
     */
    @Override
    boolean checkWithin(double cursor_x, double cursor_y) {
        if (cursor_x < this.coordinate_x - this.radius)
            return false;
        else if (cursor_x > this.coordinate_x + this.radius)
            return false;
        else if (cursor_y < this.coordinate_y - this.radius)
            return false;
        else return !(cursor_y > this.coordinate_y + this.radius);
    }

    /**
     * draw the square using its center and coordinates of four sides.
     */
    public void draw(Canvas canvas) {
        canvas.drawRect((float) (this.coordinate_x - super.radius), (float) (this.coordinate_y - super.radius),
                (float) (this.coordinate_x + super.radius), (float) (this.coordinate_y + super.radius), this.paint);
    }

}