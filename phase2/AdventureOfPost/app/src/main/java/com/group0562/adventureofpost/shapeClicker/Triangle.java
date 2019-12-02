package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import static java.lang.Math.random;
import static java.lang.Math.sqrt;

/** this class represents one of the shape that is drawn in the shapeClicker game */
public class Triangle extends Shape {

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
    Triangle(double x, double y, Paint p) {
        super(x, y, p);
        v1 = new Vertex();
        v2 = new Vertex();
        v3 = new Vertex();
    }

    /**
     * draw a equilateral triangle using its mid point of edge and length of edge.
     */
    @Override
    public void draw(Canvas canvas) {
        v1.setX(getCoordinate_x() - getRadius());
        v1.setY(getCoordinate_y());
        v2.setX(getCoordinate_x());
        v2.setY(getCoordinate_y() + sqrt(3) * getRadius());
        v3.setX(getCoordinate_x() + getRadius());
        v3.setY(getCoordinate_y());
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo((float) v1.getX(), (float) v1.getY());
        path.lineTo((float) v2.getX(), (float) v2.getY());
        path.lineTo((float) v3.getX(), (float) v3.getY());
        path.close();
        canvas.drawPath(path, getPaint());
    }

    /**
     * randomly reset the center of an edge
     */
    @Override
    public void setLocation() {
        setCoordinate_x(random() * (SCNormalMode.bound[1] - 2 * getRadius()) + getRadius());
        setCoordinate_y(random() * (SCNormalMode.bound[3] - 2 * getRadius()));
        v1.setX(getCoordinate_x() - getRadius());
        v1.setY(getCoordinate_y());
        v2.setX(getCoordinate_x());
        v2.setY(getCoordinate_y() + sqrt(3) * getRadius());
        v3.setX(getCoordinate_x() + getRadius());
        v3.setY(getCoordinate_y());
    }

    /**
     * @param cursor_x the x coordinate of the position player tap on.
     * @param cursor_y the y coordinate of the position player tap on.
     * @return a boolean whether the player taps on the triangle.
     */
    @Override
    boolean checkWithin(double cursor_x, double cursor_y) {
        if (cursor_x < v1.getX() || cursor_x > v3.getX() || cursor_y < v1.getY() || cursor_y > v2.getY()) {
            return false;
        } else if (cursor_x <= getCoordinate_x() & cursor_y - getCoordinate_y() > sqrt(3) * (cursor_x - v1.getX())) {
            return false;
        } else {
            return !(cursor_x > getCoordinate_x() & cursor_y - getCoordinate_y() > sqrt(3) * (v3.getX() - cursor_x));
        }
    }

    class Vertex {
        /**
         * x coordinate of this vertex
         */
        private double x;

        /**
         * y coordinate of this vertex
         */
        private double y;

        void setY(double y) {
            this.y = y;
        }

        void setX(double x) {
            this.x = x;
        }

        double getX() {
            return x;
        }

        double getY() {
            return y;
        }
    }
}
