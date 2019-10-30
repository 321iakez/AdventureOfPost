package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Paint;

public class Shape {
    private double coordinate_x;
    private double coordinate_y;
    private double length;
    private Paint paint;

    public Shape(double x, double y){
        this.coordinate_x = x;
        this.coordinate_y = y;
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
}
