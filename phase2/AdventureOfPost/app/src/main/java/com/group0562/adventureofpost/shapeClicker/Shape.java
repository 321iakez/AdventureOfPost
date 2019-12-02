package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Paint;
import android.graphics.Canvas;

/** this is the parent class of all shapes, have some attributes, methods for cleaner designs and easier usages for its child classes */
public abstract class Shape {

    /**
     * the x coordinate of the center point (mid point of a edge for triangles) of the shape.
     */
    private double coordinate_x;

    /**
     * the y coordinate of the center point (mid point of a edge for triangles) of the shape.
     */
    private double coordinate_y;

    /**
     * the distance from the center point of shape to one of its sides.
     */
    private double radius;

    /**
     * the paint used when drawing the shape.
     */
    private Paint paint;

    /**
     * Constructor of the shape class
     *
     * @param x the initial x coordinate of the center point (mid point of a edge for triangles) of the shape.
     * @param y the initial y coordinate of the center point (mid point of a edge for triangles) of the shape.
     */
    Shape(double x, double y, Paint p) {
        this.coordinate_x = x;
        this.coordinate_y = y;
        this.paint = p;
    }

    /** getters and setters for this class and its child classes */
    double getCoordinate_x() {
        return coordinate_x;
    }

    double getCoordinate_y() {
        return coordinate_y;
    }

    Paint getPaint() {
        return paint;
    }

    void setCoordinate_x(double coordinate_x) {
        this.coordinate_x = coordinate_x;
    }

    void setCoordinate_y(double coordinate_y) {
        this.coordinate_y = coordinate_y;
    }

    void setPaint(Paint paint) {
        this.paint = paint;
    }

    double getRadius() {
        return this.radius;
    }

    void setRadius(String level) {
        if (level.equals("Hard")) {
            this.radius = 15;
        } else {
            this.radius = 40;
        }
    }

    /**
     * different draw methods and algorithm for different shapes
     */
    public abstract void draw(Canvas canvas);

    /** two abstract methods that needs to be implemented by its child classes */
    public abstract void setLocation();

    abstract boolean checkWithin(double cursor_x, double cursor_y);

}
