package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.group0562.adventureofpost.model.PuzzleStats;

import static java.lang.Math.*;

public class Circle extends Shape{
    private Paint paint;
    final String message = "Circle";


    Circle(double x, double y, Paint p){
        super(x, y);
        this.paint = p;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)this.coordinate_x, (float) this.coordinate_y, (float)Shape.radius, this.paint);
    }

    @Override
    public void setLocation() {
        this.coordinate_x = random() * (ShapeClicker.bound[1] - 2 * Shape.radius) + Shape.radius;
        this.coordinate_y = random() * (ShapeClicker.bound[3] - 2 * Shape.radius) + Shape.radius;
    }

    @Override
    boolean checkWithin(double cursor_x, double cursor_y) {
        return (sqrt(pow(this.coordinate_x - cursor_x, 2) + pow(this.coordinate_y - cursor_y, 2)) <= Shape.radius);
    }


}
