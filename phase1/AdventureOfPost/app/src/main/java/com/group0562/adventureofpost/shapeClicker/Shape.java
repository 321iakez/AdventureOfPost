package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Paint;
import android.graphics.Canvas;

import com.group0562.adventureofpost.model.PuzzleStats;

public abstract class Shape {

    /**
     * the x coordinate of the center point (mid point of a edge for triangles) of the shape.
     */
    double coordinate_x;

    /**
     * the y coordinate of the center point (mid point of a edge for triangles) of the shape.
     */
    double coordinate_y;

    /**
     * the distance from the center point of shape to one of its sides.
     */
    static double radius;

    /**
     * the paint used when drawing the shape.
     */
    private Paint paint;

    /**
     * Constructor of the shape class
     * @param x  the initial x coordinate of the center point (mid point of a edge for triangles) of the shape.
     * @param y  the initial y coordinate of the center point (mid point of a edge for triangles) of the shape.
     */
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

    /**
     * different draw methods and algorithm for different shapes
     */
    public abstract void draw(Canvas canvas);

    public abstract void setLocation();

    abstract boolean checkWithin(double cursor_x, double cursor_y);

}
