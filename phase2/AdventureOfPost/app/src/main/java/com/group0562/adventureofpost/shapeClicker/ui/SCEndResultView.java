package com.group0562.adventureofpost.shapeClicker.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/** this is the result view that tells you if you lost or win */
public class SCEndResultView extends View {
    private Paint paint;
    private static final float Stroke_Thickness = 3;

    /**
     * boolean of whether the player finish the game with enough points.
     */
    private static boolean beat_the_game;

    /**
     * Constructor of SCEndResultView, shown to players when they press finish button
     */
    public SCEndResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);
        this.paint.setStrokeWidth(SCEndResultView.Stroke_Thickness);
        this.paint.setTextSize(50);
    }

    /**
     * Draw the text that will be seen by player when they finish the game.
     * Two different message may be displayed, depends on beat_the_game.
     */
    protected void onDraw(Canvas canvas) {
        if (SCEndResultView.beat_the_game) {
            canvas.drawText("Congrats! You have completed this puzzle!", 25, 40, this.paint);
        } else {
            canvas.drawText("Almost! Sharpen your skill and come back!", 25, 40, this.paint);
        }
    }

    /** to determine if you beat the game or not */
    public static void setBeat_the_game(boolean t) {
        SCEndResultView.beat_the_game = t;
    }
}