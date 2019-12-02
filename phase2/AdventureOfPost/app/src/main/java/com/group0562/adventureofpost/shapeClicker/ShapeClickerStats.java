package com.group0562.adventureofpost.shapeClicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.group0562.adventureofpost.database.DatabaseHelper;

/*this class serves as the class that stores all the stats we want to track for this game */
public class ShapeClickerStats {
    private Paint paint;
    private String username;
    private long time;
    private long startTime;
    private int points;
    private int lives;
    public final static String SC_STAT1 = "time";
    public final static String SC_STAT2 = "score";
    public final static String SC_STAT3 = "lives";

    /**
     * constructor of ShapeClickerStats, inherited from PuzzleStats
     *
     * @param time the time limit for SCNormalMode
     */
    ShapeClickerStats(long time, String username) {
        this.time = time;
        this.points = 0;
        this.startTime = System.currentTimeMillis();
        this.lives = 10;
        this.username = SCSetting.getUsername();
        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);
        this.paint.setStrokeWidth(3);
        this.paint.setTextSize(50);
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

    /**update the time with the system*/
    public void updateTime() {
        long currTime = System.currentTimeMillis();
        time -= (currTime - startTime);
        startTime = currTime;
    }

    /**to put the data in a form that is readable in database */
    public String getSCData(){
        return getTime() + "," + getPoints() + "," + getLives();
    }

    /**to set the data if you want to load meaning resume the game u quit before */
    public void setLoadData(String data){
        String[] data_list = data.split(",");
        this.time = (long) Double.parseDouble(data_list[0]);
        this.points = Integer.parseInt(data_list[1]);
        this.lives = Integer.parseInt(data_list[2]);
    }


    /** basically the setters and getters for this class, but has slightly different functionalities */
    void setLives(int lives) {
        this.lives -= lives;
    }

    void setPoints(int pt) {
        this.points += pt;
    }

    public void setTime(long time){
        this.time = time;
    }

    int getPoints() {
        return points;
    }

    int getLives() {
        return lives;
    }

    public long getTime() {
        return time;
    }
}
