package com.group0562.adventureofpost.sudoku;


import android.content.Context;
import android.util.Log;

import com.group0562.adventureofpost.database.DatabaseHelper;

import java.util.Observable;

public class SudokuStats extends Observable {

    public final static String SUDOKU_STAT1 = "time";
    public final static String SUDOKU_STAT2 = "moves";
    public final static String SUDOKU_STAT3 = "conflicts";

    /**
     * Number of moves user made.
     */
    private int moves = 0;

    /**
     * Number of conflict user caused.
     */
    private int conflicts = 0;

    /**
     * The current time the game is operating to.
     */
    private long gameTime = 0;

    /**
     * The time when the game starts.
     */
    private final long startTime = System.currentTimeMillis();

    /**
     * The username of the user currently playing the game.
     */
    private String username;


    public SudokuStats(String username) {
        this.username = username;
    }

    /* ========== Getters ========== */
    int getMoves() {
        return moves;
    }

    int getConflicts() {
        return conflicts;
    }

    long getGameTime() {
        return gameTime;
    }

    /* ========== Update Methods ========== */
    private void updateTime() {
        long currTime = System.currentTimeMillis();
        gameTime = currTime - startTime;

        setChanged();
        notifyObservers();
    }

    void reset() {
        moves = 0;
        conflicts = 0;

        setChanged();
        notifyObservers();
    }

    void addConflicts() {
        conflicts++;

        setChanged();
        notifyObservers();
    }

    void addMoves() {
        moves++;

        setChanged();
        notifyObservers();
    }

    /**
     * Access DatabaseHelper and stores the stats.
     */
    void saveStats(Context context) {
        DatabaseHelper db = new DatabaseHelper(context);
        long newRowId = db.insertSudokuStats(username, gameTime, conflicts, moves);
        Log.i("SudokuPresenter", "Stats inserted at row" + newRowId);
    }
}
