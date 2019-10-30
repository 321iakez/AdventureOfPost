package com.group0562.adventureofpost.shapeClicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class ShapeClickerGameView extends View {
    private static Paint paint;
    private static final float Stroke_Thickness = 3;
    private ShapeClicker clicker;

    public ShapeClickerGameView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        ShapeClickerGameView.paint = new Paint();
        ShapeClickerGameView.paint.setColor(Color.BLACK);
        ShapeClickerGameView.paint.setStrokeWidth(ShapeClickerGameView.Stroke_Thickness);
        double[] bounds = {0,800,0,1500};
        ShapeClicker.setBound(bounds);
        clicker = new ShapeClicker(60, 40, ShapeClickerGameView.paint);
    }
    public static void setColor(String color){
        ShapeClickerGameView.paint.setColor(Color.YELLOW);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.CYAN);
        clicker.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        double x = event.getX();
        double y = event.getY();
        clicker.checkWithinBall(x, y);
        invalidate();
        return super.onTouchEvent(event);
    }
}
