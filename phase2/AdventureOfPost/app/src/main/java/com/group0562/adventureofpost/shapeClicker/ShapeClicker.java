package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.group0562.adventureofpost.AdventureOfPost;
import com.group0562.adventureofpost.Puzzles;

public abstract class ShapeClicker extends Puzzles {
    ShapeClicker(AdventureOfPost.PuzzleStats statsInst){
        super(statsInst);
    }
    abstract void draw(Canvas canvas);
    abstract void checkWithinBall(double cursor_x, double cursor_y);

}
