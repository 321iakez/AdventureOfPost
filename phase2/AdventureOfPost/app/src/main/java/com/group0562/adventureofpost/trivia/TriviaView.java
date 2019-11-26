package com.group0562.adventureofpost.trivia;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.group0562.adventureofpost.model.PuzzleStats;

import java.io.Serializable;

public class TriviaView extends View implements Serializable {
    /**
     * paint used for display
     */
    private static Paint paint;

    /**
     * stroke width of the paint
     */
    private static final float Stroke_Thickness = 3;

    Trivia trivia = new Trivia();
    /**
     * the three stats of the puzzle
     */
    PuzzleStats puzzleStats;

    public TriviaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TriviaView.paint = new Paint();
        TriviaView.paint.setColor(Color.BLACK);
        TriviaView.paint.setStrokeWidth(TriviaView.Stroke_Thickness);
        this.puzzleStats = trivia.puzzleStats;
    }

    //TODO Figure this out
    /*
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.CYAN);
        this.trivia.draw(canvas);
        this.puzzleStats.draw(canvas);
        this.puzzleStats.updateTime();
        invalidate();
    }
    */
}
