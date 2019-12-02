package com.group0562.adventureofpost.sudoku;


import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import com.group0562.adventureofpost.database.DatabaseHelper;

import java.util.List;
import java.util.Observable;


/**
 * Sudoku stats tracking class.
 */
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
     * The username of the user currently playing the game.
     */
    private String username;


    public SudokuStats(String username) {
        this.username = username;
    }

    public SudokuStats(List<String> game_stats){
        moves = Integer.parseInt(game_stats.get(0));
        conflicts = Integer.parseInt(game_stats.get(1));
        gameTime = Integer.parseInt(game_stats.get(2));
        username = game_stats.get(3);
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

    String getUsername(){return username;}
    /* ========== Update Methods ========== */
    void updateTime(int time) {
        gameTime = time;

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

    /**
     * Counter up timer class for Sudoku.
     */
    abstract static class CountUpTimer extends CountDownTimer {
        private static final long INTERVAL_MS = 1000;
        private final long duration;

        CountUpTimer(long durationMs) {
            super(durationMs, INTERVAL_MS);
            this.duration = durationMs;
        }

        abstract void onTick(int second);

        @Override
        public void onTick(long msUntilFinished) {
            int second = (int) ((duration - msUntilFinished) / 1000);
            onTick(second);
        }

        @Override
        public void onFinish() {
            onTick(duration / 1000);
        }
    }
}
