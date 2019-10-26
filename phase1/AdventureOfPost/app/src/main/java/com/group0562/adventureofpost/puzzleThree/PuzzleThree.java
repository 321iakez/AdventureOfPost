package com.group0562.adventureofpost.puzzleThree;

import com.group0562.adventureofpost.Puzzles;


public class PuzzleThree extends Puzzles {
    /* bound indexes follow from left, right, up and down */
    private static double[] bound;
    private double center_x;
    private double center_y;
    private double r;
    private int points;

    public PuzzleThree(long time, double radius, double[] bounds) {

        super(time);
        this.points = 0;
        this.r = radius;
        this.setBallLocation();
        PuzzleThree.bound = bounds;
    }

    private void setBallLocation(){
        this.center_x = Math.random()*(PuzzleThree.bound[1]-2*this.r) + this.r;
        this.center_y = Math.random()*(PuzzleThree.bound[3]-2*this.r) + this.r;
    }

    @Override
    public void updatePoints() {

    }

    @Override
    public void checkComplete() {

    }
}
