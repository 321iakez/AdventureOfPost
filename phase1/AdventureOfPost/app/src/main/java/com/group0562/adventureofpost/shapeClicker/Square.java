package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import static java.lang.Math.*;

public class Square extends Shape {
    private double radius;
    private Paint paint;

    public Square(double x, double y, double r, Paint p){
        super(x, y, r);
        this.paint = p;

    }

    @Override
    public void setLocation() {
        this.coordinate_x = random() * (ShapeClicker.bound[1] - 2 * this.radius) + this.radius;
        this.coordinate_y = random() * (ShapeClicker.bound[3] - 2 * this.radius) + this.radius;
    }

    @Override
    boolean checkWithin(double cursor_x, double cursor_y) {
        if(cursor_x < this.coordinate_x - this.radius)
            return false;
        else if(cursor_x > this.coordinate_x + this.radius)
            return false;
        else if(cursor_y < this.coordinate_y - this.radius)
            return false;
        else if (cursor_y > this.coordinate_y +this.radius)
            return false;
        else
            return true;
    }

    public void draw(Canvas canvas){
        canvas.drawRect((float)(this.coordinate_x-this.radius), (float)(this.coordinate_y + this.radius),
                (float)(this.coordinate_x + this.radius), (float)(this.coordinate_y + this.radius), paint);
    }

}