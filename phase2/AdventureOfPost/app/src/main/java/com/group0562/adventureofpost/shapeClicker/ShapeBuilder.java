package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

public class ShapeBuilder {

    private ArrayList<Shape> s_objects;

    ShapeBuilder(int goal, int num_of_shapes, Paint paint) {
        s_objects = new ArrayList<>();
        randomShapeGenerator(goal, num_of_shapes, paint);
    }

    private void randomShapeGenerator(int goal, int num_of_shapes, Paint paint) {
        //algorithm that makes the most to least number of circle, square and then triangle, and then create them

        double[] temp_location = {50, 125};
        double x_interval = 100;
        double y_interval = 150;
        for (int i = 50; i <= ShapeClicker.bound[1]; i += x_interval) {
            for (int j = 125; j <= ShapeClicker.bound[3]; j += y_interval) {
                if (new Random().nextDouble() <= 0.1) {
                    s_objects.add(new Triangle(temp_location[0], temp_location[1], paint));
                } else if (new Random().nextDouble() <= 0.1) {
                    continue;
                } else if (new Random().nextDouble() <= 0.3) {
                    s_objects.add(new Square(temp_location[0], temp_location[1], paint));
                } else {
                    s_objects.add(new Circle(temp_location[0], temp_location[1], paint));
                }
                temp_location[0] = i;
                temp_location[1] = j;
            }
        }
    }

    ArrayList<Shape> getS_objects() {
        return this.s_objects;
    }
}
