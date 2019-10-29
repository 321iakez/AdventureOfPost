package com.group0562.adventureofpost.circleClicker;

import android.graphics.Canvas;

public class CircleClickerManager {
    private CircleClicker clicker;
    private Circle circle;

    public CircleClickerManager(){
        double[] bounds = {0.0,30.0,0.0,30.0};
        clicker = new CircleClicker(60, 15, bounds);
    }
    public void draw(Canvas canvas){
        clicker.draw(canvas);
    }
}
