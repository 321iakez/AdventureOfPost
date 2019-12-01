package com.group0562.adventureofpost.shapeClicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.group0562.adventureofpost.AdventureOfPost;
import com.group0562.adventureofpost.database.DatabaseHelper;

import java.util.Observable;


public class ShapeClickerStats extends AdventureOfPost.PuzzleStats {

    public final static String SC_STAT1 = "time";
    public final static String SC_STAT2 = "score";
    public final static String SC_STAT3 = "lives";

    private Paint paint;
    private static long TIME_LIMIT;
    private String username;

    /**
     * constructor of ShapeClickerStats, inherited from PuzzleStats
     *
     * @param time the time limit for SCNormalMode
     */
    public ShapeClickerStats(long time, String username) {
        super(time);
        TIME_LIMIT = time;
        this.username = username;
        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);
        this.paint.setStrokeWidth(3);
        this.paint.setTextSize(50);
    }

    /**
     * update the time and points for the SCNormalMode
     */
    @Override
    public void update(Observable o, Object arg) {
        updateTime();
        updatePoints();
    }

    @Override
    public void updatePoints() {
        setPoints(1);
    }

    /**
     * Show the remaining time, remaining lives and points gained to the player
     */
    public void draw(Canvas canvas) {
        String time_text;
        String lives_text;
        if (this.getTime() >= 0) {
            time_text = "Time: " + (int) this.getTime() / 1000;
        } else {
            time_text = "Time: 0";
        }
        if (this.getLives() > 0) {
            lives_text = "Lives: " + this.getLives();
        } else {
            lives_text = "Lives: 0";
        }
        String points_text = "Points: " + this.getPoints();
        String combined = time_text + " " + points_text + " " + lives_text;
        canvas.drawText(combined, 25, 40, paint);
    }

    void saveStats(Context context) {
        DatabaseHelper db = new DatabaseHelper(context);
        long newRowId = db.insertSudokuStats(this.username, (long)getTime(), getPoints(), getLives());
        Log.i("ShapeClicker", "Stats inserted at row" + newRowId);
    }
}
