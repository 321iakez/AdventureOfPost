package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import static java.lang.Math.*;

public class Circle extends Shape{
    private double coordinate_x;
    private double coordinate_y;
    private double radius;
    private Paint paint;

    public Circle(float x, float y, float r, Paint p){
        super(x, y);
        this.coordinate_x = x;
        this.coordinate_y = y;
        this.radius = r;
        this.paint = p;
    }
    public void draw(Canvas canvas){
        canvas.drawCircle((float)this.coordinate_x, (float) this.coordinate_y, (float)this.radius, this.paint);
    }

    void setBallLocation() {
        this.coordinate_x = random() * (ShapeClicker.bound[1] - 2 * this.radius) + this.radius;
        this.coordinate_y = random() * (ShapeClicker.bound[3] - 2 * this.radius) + this.radius;
    }

    boolean checkWithinBall(double cursor_x, double cursor_y) {
        return (sqrt(pow(this.coordinate_x - cursor_x, 2) + pow(this.coordinate_y - cursor_y, 2)) <= this.radius);
    }
}
