package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.group0562.adventureofpost.model.PuzzleStats;

import java.util.Observable;

public class ShapeClickerStats extends PuzzleStats {
    private Paint paint;
    public ShapeClickerStats(long time) {
        super(time);
        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);
        this.paint.setStrokeWidth(3);
        this.paint.setTextSize(50);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateTime();
        updatePoints();
    }

    @Override
    public void updatePoints() {
        setPoints(1);
    }

    public void draw(Canvas canvas){
        String time_text = "Time: " + this.getTime()/1000;
        String points_text = "Points: " + this.getPoints();
        String lives_text = "Lives: " + this.getLives();
        String combined = time_text + " " + points_text + " " + lives_text;
        canvas.drawText(combined, 25, 40, paint);
    }
}
