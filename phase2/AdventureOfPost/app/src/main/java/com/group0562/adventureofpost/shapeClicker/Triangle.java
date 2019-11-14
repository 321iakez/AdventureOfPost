package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.group0562.adventureofpost.model.PuzzleStats;

import static java.lang.Math.*;

public class Triangle extends Shape {
    private Paint paint;
    final String message = "Triangle";

    /**
     * three vertices of this triangle.
     */
    private Vertex v1;
    private Vertex v2;
    private Vertex v3;

    /**
     * constructor for a triangle, including setting the coordinates of its three vertices and
     * mid point of an edge
     */
    public Triangle(double x, double y, Paint p) {
        super(x, y);
        this.paint = p;
        this.v1 = new Vertex();
        this.v2 = new Vertex();
        this.v3 = new Vertex();
        v1.x = this.coordinate_x - Shape.radius;
        v1.y = this.coordinate_y;
        v2.x = this.coordinate_x;
        v2.y = this.coordinate_y + sqrt(3) * Shape.radius;
        v3.x = this.coordinate_x + Shape.radius;
        v3.y = this.coordinate_y;
    }

    /**
     * draw a equilateral triangle using its mid point of edge and length of edge.
     */
    @Override
    public void draw(Canvas canvas) {
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo((float) v1.x, (float) v1.y);
        path.lineTo((float) v2.x, (float) v2.y);
        path.lineTo((float) v3.x, (float) v3.y);
        path.close();
        canvas.drawPath(path, this.paint);

    }

    /**
     * randomly reset the center of an edge
     */
    @Override
    public void setLocation() {
        this.coordinate_x = random() * (ShapeClicker.bound[1] - 2 * Shape.radius) + Shape.radius;
        this.coordinate_y = random() * (ShapeClicker.bound[3] - 2 * Shape.radius);
        v1.x = this.coordinate_x - Shape.radius;
        v1.y = this.coordinate_y;
        v2.x = this.coordinate_x;
        v2.y = this.coordinate_y + sqrt(3) * Shape.radius;
        v3.x = this.coordinate_x + Shape.radius;
        v3.y = this.coordinate_y;

    }

    /**
     * @param cursor_x the x coordinate of the position player tap on.
     * @param cursor_y the y coordinate of the position player tap on.
     * @return a boolean whether the player taps on the triangle.
     */
    @Override
    boolean checkWithin(double cursor_x, double cursor_y) {
        if (cursor_x < v1.x)
            return false;
        else if (cursor_x > v3.x)
            return false;
        else if (cursor_y < v1.y)
            return false;
        else if (cursor_y > v2.y)
            return false;
        else if (cursor_x <= this.coordinate_x & cursor_y - this.coordinate_y > sqrt(3) * (cursor_x - v1.x))
            return false;
        else if (cursor_x > this.coordinate_x & cursor_y - this.coordinate_y > sqrt(3) * (v3.x - cursor_x))
            return false;
        return true;
    }
}
