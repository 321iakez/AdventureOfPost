package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Paint;
import android.graphics.Canvas;

public abstract class Shape {
    double coordinate_x;
    double coordinate_y;
    static double radius;
    private Paint paint;

    Shape(double x, double y){
        this.coordinate_x = x;
        this.coordinate_y = y;
    }

    double getCoordinate_x() {
        return coordinate_x;
    }

    double getCoordinate_y() {
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

    public static double getRadius(){return Shape.radius;}

    public static void setRadius(String level) {
        if (level.equals("Hard")){Shape.radius = 15;}
        else {Shape.radius = 40;}
    }

    public abstract void draw(Canvas canvas);

    public abstract void setLocation();

    abstract boolean checkWithin(double cursor_x, double cursor_y);

}
