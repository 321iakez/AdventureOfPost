package com.group0562.adventureofpost.model;

public class PuzzleStats {

    private long time;
    private int points;
    private int lives;

    public PuzzleStats(long time) {
        this.time = time;
        this.points = 0;
        this.lives = 1;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void elapseTime(long timeElapsed) {
        time -= timeElapsed;
    }

    public void setPoints(int pt) { this.points += pt; }

    public int getPoints() { return points; }

    public int getLives() {
        return lives;
    }

    public double getTime() {
        return time;
    }
}
