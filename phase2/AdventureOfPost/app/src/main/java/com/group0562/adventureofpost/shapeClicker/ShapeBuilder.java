package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

/** this is a class to build bunch of shapes for the fancymode presenter for the shapeClicker game */
public class ShapeBuilder {

    private ArrayList<Shape> s_objects;

    /**constructor of this class */
    ShapeBuilder(Paint paint) {
        s_objects = new ArrayList<>();
        shapeGenerator(paint);
    }

    private void shapeGenerator(Paint paint) {
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

    /**getters and setters for this class */
    ArrayList<Shape> getS_objects() {
        return this.s_objects;
    }
}
