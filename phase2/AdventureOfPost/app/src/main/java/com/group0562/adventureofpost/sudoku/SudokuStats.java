package com.group0562.adventureofpost.sudoku;

import com.group0562.adventureofpost.AdventureOfPost;

import java.util.Observable;

public class SudokuStats extends AdventureOfPost.PuzzleStats {

    /**
     * Number of moves user made.
     */
    private int moves;

    /**
     * Number of conflict user caused.
     */
    private int conflicts;

    public SudokuStats(long time) {
        super(time);
    }

    @Override
    public void updatePoints() {

    }

    @Override
    public void updateTime(){
        long currTime = System.currentTimeMillis();
        this.setTime(currTime - getStartTime());
    }

    @Override
    public void update(Observable o, Object arg) {
        updateTime();
        updatePoints();
    }

    void reset() {
        moves = 0;
        conflicts = 0;
    }

    int getMoves() {
        return moves;
    }

    int getConflicts() {
        return conflicts;
    }

    void addConflicts() {
        conflicts++;
    }

    void addMoves() {
        moves++;
    }
}
