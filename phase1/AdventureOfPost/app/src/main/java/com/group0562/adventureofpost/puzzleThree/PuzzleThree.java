package com.group0562.adventureofpost.puzzleThree;

import com.group0562.adventureofpost.Puzzles;


public class PuzzleThree extends Puzzles {
    /* bound indexes follow from left, right, up and down */
    private static double[] bound;
    private double center_x;
    private double center_y;
    private double r;
    private boolean within = false;

    public PuzzleThree(long time, double radius, double[] bounds) {

        super(new PuzzleThreeStats(time));
        this.r = radius;
        this.setBallLocation();
        PuzzleThree.bound = bounds;
    }

    private void setBallLocation() {
        this.center_x = Math.random() * (PuzzleThree.bound[1] - 2 * this.r) + this.r;
        this.center_y = Math.random() * (PuzzleThree.bound[3] - 2 * this.r) + this.r;
    }

    // call this before update in front end
    void checkWithinBall(int cursor_x, int cursor_y) {
        if (Math.sqrt(Math.pow(this.center_x - cursor_x, 2) + Math.pow(this.center_y - cursor_y, 2)) <= r) {
            this.within = true;
        }
    }

//    @Override
//    public void updatePoints() {
//        if (this.within) {
//            this.puzzleStats.setPoints(1);
//            this.setBallLocation();
//        }
//
//    }

    @Override
    public void update() {
        super.update();

        if (this.within) {
            notifyObservers();
            this.setBallLocation();
        }
    }

    @Override
    public void checkComplete() {
        if (this.puzzleStats.getPoints() >= 50) {
            this.setPuzzleComplete(true);
            this.puzzleStats.setPoints((int) this.puzzleStats.getTime());
        }
    }
}
