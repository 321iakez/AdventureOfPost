package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Paint;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Math.random;

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
        double[] temp_location = {0, 0};
        double x_interval = SCFancyMode.bound[1]/num_of_shapes;
        double y_interval = SCFancyMode.bound[3]/num_of_shapes;
        for(int i = 0; i <= Math.floor(num_of_shapes*4/10);i++){
          temp_location[0] = temp_location[0] + x_interval;
          temp_location[1] = temp_location[1] + y_interval;
          this.s_objects.add(new Circle(temp_location[0], temp_location[1], paint));
        }
        for(int i = 0; i <= Math.floor(num_of_shapes*3/10);i++){
            temp_location[0] = temp_location[0] + x_interval;
            temp_location[1] = temp_location[1] + y_interval;
            this.s_objects.add(new Square(temp_location[0], temp_location[1], paint));
        }
        for(int i = 0; i <= Math.floor(num_of_shapes*3/10);i++){
            temp_location[0] = temp_location[0] + x_interval;
            temp_location[1] = temp_location[1] + y_interval;
            this.s_objects.add(new Triangle(temp_location[0], temp_location[1], paint));
        }
    }

    ArrayList<Shape> getS_objects(){return this.s_objects;}
}
