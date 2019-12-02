package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import static java.lang.Math.*;

/** this class represents one of the shape that is drawn in the shapeClicker game */
public class Square extends Shape {

    /**
     * Constructor of a Square, including setting the coordinates of its center
     */
    Square(double x, double y, Paint p) {
        super(x, y, p);
    }

    /**
     * randomly reset the center of square
     */
    @Override
    public void setLocation() {
        setCoordinate_x(random() * (SCNormalMode.bound[1] - 2 * getRadius()) + getRadius());
        setCoordinate_y(random() * (SCNormalMode.bound[3] - 2 * getRadius()) + getRadius());
    }

    /**
     * @param cursor_x the x coordinate of the position player tap on.
     * @param cursor_y the y coordinate of the position player tap on.
     * @return a boolean whether the player taps on the square.
     */
    @Override
    boolean checkWithin(double cursor_x, double cursor_y) {
        if (cursor_x < getCoordinate_x() - getRadius())
            return false;
        else if (cursor_x > getCoordinate_x() + getRadius())
            return false;
        else if (cursor_y < getCoordinate_y() - getRadius())
            return false;
        else return !(cursor_y > getCoordinate_y() + getRadius());
    }

    /**
     * draw the square using its center and coordinates of four sides.
     */
    public void draw(Canvas canvas) {
        canvas.drawRect((float) (getCoordinate_x() - super.getRadius()),
                (float) (getCoordinate_y() - super.getRadius()),
                (float) (getCoordinate_x() + super.getRadius()),
                (float) (getCoordinate_y() + super.getRadius()),
                getPaint());
    }

}