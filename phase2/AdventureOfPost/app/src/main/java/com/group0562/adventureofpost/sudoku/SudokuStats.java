package com.group0562.adventureofpost.sudoku;


import java.util.Observable;

public class SudokuStats {

    /**
     * Number of moves user made.
     */
    private int moves;

    /**
     * Number of conflict user caused.
     */
    private int conflicts;
    private long gameTime;
    private long startTime;


    public SudokuStats() {
        moves = 0;
        conflicts = 0;
        startTime = System.currentTimeMillis();
        gameTime = 0;
    }


    public void updatePoints() {

    }


    public void updateTime() {
        long currTime = System.currentTimeMillis();
        this.setGameTime(currTime - startTime);
    }


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

    long getGameTime() {
        return gameTime;
    }

    void setGameTime(long gameTime) {
        this.gameTime = gameTime;
    }

    void addConflicts() {
        conflicts++;
    }

    void addMoves() {
        moves++;
    }
}
