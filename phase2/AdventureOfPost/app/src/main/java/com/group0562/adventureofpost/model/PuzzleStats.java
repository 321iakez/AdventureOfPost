package com.group0562.adventureofpost.model;

import android.graphics.Canvas;

import java.util.Observer;

public abstract class PuzzleStats implements Observer {

    private long time;
    private long startTime;
    private int points;
    private int lives;

    public PuzzleStats(){}

    public PuzzleStats(long time) {
        this.time = time;
        this.points = 0;
        this.startTime = System.currentTimeMillis();
        this.lives = 10;
    }

    public void setLives(int lives) {
        this.lives -= lives;
    }

    public void setPoints(int pt) {
        this.points += pt;
    }

    public void setTime(long time){this.time = time;}

    public int getPoints() {
        return points;
    }

    public int getLives() {
        return lives;
    }

    public double getTime() {
        return time;
    }

    public long getStartTime(){return startTime;}

    public void updateTime() {
        long currTime = System.currentTimeMillis();
        time -= (currTime - startTime);
        startTime = currTime;
    }

    public abstract void updatePoints();

    public void draw(Canvas canvas){};
}
