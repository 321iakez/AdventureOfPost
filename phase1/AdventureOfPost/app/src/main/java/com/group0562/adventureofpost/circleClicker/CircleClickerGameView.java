package com.group0562.adventureofpost.circleClicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleClickerGameView extends View {
    private Paint paint;
    private static final float Stroke_Thickness = 3;

    public CircleClickerGameView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);
        this.paint.setStrokeWidth(CircleClickerGameView.Stroke_Thickness);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.CYAN);
    }
}
