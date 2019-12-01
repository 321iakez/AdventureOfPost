package com.group0562.adventureofpost.shapeClicker.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.group0562.adventureofpost.shapeClicker.SCScoreboardPresenter;

public class SCScoreboardView extends View {
    private Paint paint;

    private static final float Stroke_Thickness = 5;
    private SCScoreboardPresenter presenter;

    public SCScoreboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);
        this.paint.setStrokeWidth(SCScoreboardView.Stroke_Thickness);
        this.paint.setTextSize(50);
    }

    protected void onDraw(Canvas canvas){
        String player = (String) presenter.getTopPlayer().get(0);
        String points = (String) presenter.getTopPlayer().get(1);
        canvas.drawText("Top player: "+ player + " with "+ points+ ", "+" points", 25,40, this.paint);
    }
}
