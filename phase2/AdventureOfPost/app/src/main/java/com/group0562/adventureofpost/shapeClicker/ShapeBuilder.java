package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Paint;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Math.random;

public class ShapeBuilder {
    private ArrayList<Shape> s_objects;
    private ArrayList<double[]> locations;
    final int circle_score = 1;
    final int square_score = 2;
    final int triangle_score = 3;

    ShapeBuilder(int goal, int num_of_shapes, Paint paint){
        this.s_objects = new ArrayList<Shape>();
        this.locations = new ArrayList<double[]>();
        randomShapeGenerator(goal, num_of_shapes, paint);
    }

    private void randomShapeGenerator(int goal, int num_of_shapes, Paint paint){
        //algorithm that makes the most to least number of circle, square and then triangle, and then create them
        double[] temp_location = {0, 0};
        //this.locations.add(temp_location);
        for(int i = 0; i <= Math.floor(num_of_shapes*4/5);i++){
            temp_location[0] = random() * (SCDifficultMode.bound[1] - 2 * Shape.radius) + Shape.radius;
            temp_location[1] = random() * (SCDifficultMode.bound[3] - 2 * Shape.radius) + Shape.radius;
            //if (!this.locations.contains(temp_location)){
                this.s_objects.add(new Circle(temp_location[0], temp_location[1], paint));
                this.locations.add(temp_location);
            //}
        }
        for(int i = 0; i <= Math.floor(num_of_shapes*2/5);i++){
            temp_location[0] = random() * (SCDifficultMode.bound[1] - 2 * Shape.radius) + Shape.radius;
            temp_location[1] = random() * (SCDifficultMode.bound[3] - 2 * Shape.radius) + Shape.radius;
            //if (!this.locations.contains(temp_location)){
                this.s_objects.add(new Square(temp_location[0], temp_location[1], paint));
                this.locations.add(temp_location);
            //}
        }
        for(int i = 0; i <= Math.floor(num_of_shapes*2/5);i++){
            temp_location[0] = random() * (SCDifficultMode.bound[1] - 2 * Shape.radius) + Shape.radius;
            temp_location[1] = random() * (SCDifficultMode.bound[3] - 2 * Shape.radius);
            //if (!this.locations.contains(temp_location)){
                this.s_objects.add(new Triangle(temp_location[0], temp_location[1], paint));
                this.locations.add(temp_location);
            //}
        }

        System.out.println(this.s_objects.size());

    }

    ArrayList<Shape> getS_objects(){return this.s_objects;}
}
