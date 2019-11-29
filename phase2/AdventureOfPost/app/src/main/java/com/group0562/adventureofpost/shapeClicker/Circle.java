package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import static java.lang.Math.*;

public class Circle extends Shape {
    private Paint paint;
    final String message = "Circle";

    /**
     * Constructor of a Circle, including setting the coordinates of its center.
     */
    Circle(double x, double y, Paint p) {
        super(x, y);
        this.paint = p;
    }

    /**
     * draw the circle using its center and radius
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float) this.coordinate_x, (float) this.coordinate_y, (float) this.radius, this.paint);
    }

    /**
     * randomly reset the center of circle
     */
    @Override
    public void setLocation() {
        this.coordinate_x = random() * (SCNormalMode.bound[1] - 2 * this.radius) + this.radius;
        this.coordinate_y = random() * (SCNormalMode.bound[3] - 2 * this.radius) + this.radius;
    }

    /**
     * @param cursor_x the x coordinate of the position player tap on.
     * @param cursor_y the y coordinate of the position player tap on.
     * @return a boolean whether the player taps on the circle.
     */
    @Override
    boolean checkWithin(double cursor_x, double cursor_y) {
        return (sqrt(pow(this.coordinate_x - cursor_x, 2) + pow(this.coordinate_y - cursor_y, 2)) <= this.radius);
    }


}
