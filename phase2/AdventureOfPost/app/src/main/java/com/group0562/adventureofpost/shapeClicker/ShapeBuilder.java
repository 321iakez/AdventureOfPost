package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Paint;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShapeBuilder {
    private ArrayList<Shape> s_objects;
    final int circle_score = 1;
    final int square_score = 2;
    final int triangle_score = 3;

    ShapeBuilder(int goal, int num_of_shapes, Paint paint){
        this.s_objects = new ArrayList<Shape>();
        randomShapeGenerator(goal, num_of_shapes, paint);
    }

    private void randomShapeGenerator(int goal, int num_of_shapes, Paint paint){
        //algorithm that makes the most to least number of circle, square and then triangle, and then create them
        this.s_objects.add(new Circle(15, 15, paint));
        this.s_objects.add(new Square(30, 30, paint));
    }

    ArrayList<Shape> getS_objects(){return this.s_objects;}
}
