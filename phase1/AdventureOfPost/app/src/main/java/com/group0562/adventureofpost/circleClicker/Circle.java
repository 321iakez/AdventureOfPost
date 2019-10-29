package com.group0562.adventureofpost.circleClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle {
    private float coordinate_x;
    private float coordinate_y;
    private float radius;
    private Paint paint;

    public Circle(float x, float y, float r, Paint p){
        this.coordinate_x = x;
        this.coordinate_y = y;
        this.radius = r;
        this.paint = p;
    }
    public void draw(Canvas canvas){
        canvas.drawCircle(this.coordinate_x, this.coordinate_y, this.radius, this.paint);
    }
}
