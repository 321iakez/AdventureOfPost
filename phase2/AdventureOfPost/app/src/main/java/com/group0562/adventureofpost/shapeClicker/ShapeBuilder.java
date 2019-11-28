package com.group0562.adventureofpost.shapeClicker;

import java.util.ArrayList;

public class ShapeBuilder {
    private ArrayList<Shape> s_objects;
    final int circle_score = 1;
    final int square_score = 2;
    final int triangle_score = 3;

    ShapeBuilder(int goal, int num_of_shapes){
        randomShapeGenerator(goal, num_of_shapes);
    }

    private void randomShapeGenerator(int goal, int num_of_shapes){
        //algorithm that makes the most to least number of circle, square and then triangle, and then create them
    }

    ArrayList<Shape> getS_objects(){return this.getS_objects();}
}
