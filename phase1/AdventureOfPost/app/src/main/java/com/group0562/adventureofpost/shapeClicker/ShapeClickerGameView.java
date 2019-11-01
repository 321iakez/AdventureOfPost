package com.group0562.adventureofpost.shapeClicker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.os.Bundle;


import androidx.annotation.Nullable;

import com.group0562.adventureofpost.model.PuzzleStats;
import com.group0562.adventureofpost.shapeClicker.views.SCSettingActivity;
import com.group0562.adventureofpost.shapeClicker.views.ShapeClickerActivity;
import com.group0562.adventureofpost.shapeClicker.views.ShapeClickerEndActivity;

public class ShapeClickerGameView extends View {
    private static Paint paint;
    private static final float Stroke_Thickness = 3;
    private ShapeClicker clicker;
    PuzzleStats puzzleStats;

    public ShapeClickerGameView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        ShapeClickerGameView.paint = new Paint();
        ShapeClickerGameView.paint.setColor(Color.BLACK);
        ShapeClickerGameView.paint.setStrokeWidth(ShapeClickerGameView.Stroke_Thickness);
        double[] bounds = {0,800,0,1500};
        ShapeClicker.setBound(bounds);
        clicker = new ShapeClicker(60000, ShapeClickerGameView.paint);
        Shape.setRadius("Easy");
        this.puzzleStats = clicker.puzzleStats;
    }

    public static void setColor(String color){
        if(color.equals("Black")) {ShapeClickerGameView.paint.setColor(Color.BLACK);}
        else if(color.equals("White")) {ShapeClickerGameView.paint.setColor(Color.WHITE);}
        else if(color.equals("Blue")) {ShapeClickerGameView.paint.setColor(Color.BLUE);}
        else if(color.equals("Yellow")) {ShapeClickerGameView.paint.setColor(Color.YELLOW);}
        else {ShapeClickerGameView.paint.setColor(Color.GREEN);}
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.CYAN);
        this.clicker.draw(canvas);
        this.puzzleStats.draw(canvas);
        this.puzzleStats.updateTime();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        double x = event.getX();
        double y = event.getY();
        this.clicker.checkWithinBall(x, y);
        invalidate();
        return super.onTouchEvent(event);
    }
}
