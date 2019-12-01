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

    /**
     * paint used for display
     */
    private static Paint paint = new Paint();

    /**
     * stroke width of the paint
     */
    private static final float Stroke_Thickness = 3;

    /**
     * the SCNormalMode that is used in the view to be displayed
     */
    private ShapeClicker clicker;


    /**
     * the three stats of the puzzle
     */
    ShapeClickerStats puzzleStats;

    /**
     * constructor for ShapeClickerGameView for player to react with the puzzle, inherit the View class
     */
    public ShapeClickerGameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ShapeClickerGameView.paint = new Paint();
        this.setColor(SCSetting.getColor());
        ShapeClickerGameView.paint.setStrokeWidth(ShapeClickerGameView.Stroke_Thickness);
        double[] bounds = {0, 1000, 30, 1500};
        ShapeClicker.setBound(bounds);
        if(SCSetting.getMode().equals("Normal")) {
            clicker = new SCNormalMode(60000, ShapeClickerGameView.paint);
        }
        else{
            clicker = new SCFancyMode(60000, ShapeClickerGameView.paint);
        }
        this.puzzleStats = clicker.puzzleStats;
    }

    /**
     * Change the color of shapes displayed
     *
     * @param color the color being changed to
     */
    public void setColor(String color) {
        if (color.equals("Black")) {
            ShapeClickerGameView.paint.setColor(Color.BLACK);
        } else if (color.equals("White")) {
            ShapeClickerGameView.paint.setColor(Color.WHITE);
        } else if (color.equals("Blue")) {
            ShapeClickerGameView.paint.setColor(Color.BLUE);
        } else if (color.equals("Yellow")) {
            ShapeClickerGameView.paint.setColor(Color.YELLOW);
        } else {
            ShapeClickerGameView.paint.setColor(Color.GREEN);
        }
    }

    /**
     * This method is called to draw the shapes and stats on the screen
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.CYAN);
        this.clicker.draw(canvas);
        this.puzzleStats.draw(canvas);
        this.puzzleStats.updateTime();
        invalidate();
    }

    /**
     * This method is called when the player click on the screen, with x and y coordinates
     * Checks if the player taps within the shape
     *
     * @param event the tapping of player on screen
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        double x = event.getX();
        double y = event.getY();
        this.clicker.checkWithinBall(x, y);
        invalidate();
        return super.onTouchEvent(event);
    }

    public ShapeClicker getClicker() {
        return clicker;
    }
}
