package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import static java.lang.Math.pow;
import static java.lang.Math.random;
import static java.lang.Math.sqrt;

/** this class represents one of the shape that is drawn in the shapeClicker game */
public class Circle extends Shape {

    /**
     * Constructor of a Circle, including setting the coordinates of its center.
     */
    Circle(double x, double y, Paint p) {
        super(x, y, p);
    }

    /**
     * draw the circle using its center and radius
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float) getCoordinate_x(), (float) getCoordinate_y(), (float) getRadius(), getPaint());
    }

    /**
     * randomly reset the center of circle
     */
    @Override
    public void setLocation() {
        setCoordinate_x(random() * (SCNormalMode.bound[1] - 2 * getRadius()) + getRadius());
        setCoordinate_y(random() * (SCNormalMode.bound[3] - 2 * getRadius()) + getRadius());
    }

    /**
     * @param cursor_x the x coordinate of the position player tap on.
     * @param cursor_y the y coordinate of the position player tap on.
     * @return a boolean whether the player taps on the circle.
     */
    @Override
    boolean checkWithin(double cursor_x, double cursor_y) {
        return (sqrt(pow(getCoordinate_x() - cursor_x, 2) + pow(getCoordinate_y() - cursor_y, 2)) <= getRadius());
    }
}
