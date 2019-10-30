package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Paint;
import android.graphics.Canvas;

public abstract class Shape {
    protected double coordinate_x;
    protected double coordinate_y;
    protected double radius;
    private Paint paint;

    public Shape(double x, double y, double r){
        this.coordinate_x = x;
        this.coordinate_y = y;
        this.radius = r;
    }

    public double getCoordinate_x() {
        return coordinate_x;
    }

    public double getCoordinate_y() {
        return coordinate_y;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setCoordinate_x(double coordinate_x) {
        this.coordinate_x = coordinate_x;
    }

    public void setCoordinate_y(double coordinate_y) {
        this.coordinate_y = coordinate_y;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public abstract void draw(Canvas canvas);

    public abstract void setLocation();

    abstract boolean checkWithin(double cursor_x, double cursor_y);

}
