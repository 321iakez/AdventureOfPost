package com.group0562.adventureofpost.shapeClicker.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SCEndResultView extends View {
    private Paint paint;
    private static final float Stroke_Thickness = 3;
    private static boolean beat_the_game;

    public SCEndResultView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);
        this.paint.setStrokeWidth(SCEndResultView.Stroke_Thickness);
        this.paint.setTextSize(50);
    }

    protected void onDraw(Canvas canvas) {
        if(SCEndResultView.beat_the_game) {
            canvas.drawText("Congrats! You have completed this puzzle!", 25, 40, this.paint);
        }
        else{canvas.drawText("Almost! Sharpen your skill and come back!", 25, 40, this.paint);}
    }

    public static void setBeat_the_game(boolean t){
        SCEndResultView.beat_the_game = t;
    }
}