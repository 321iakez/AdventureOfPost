package com.group0562.adventureofpost.trivia;


import android.content.Context;
import android.util.Log;

import com.group0562.adventureofpost.database.DatabaseHelper;

/**
 * This is the TriviaStats class, it keeps Tracks of the 3 Stats of the user
 * and the Username of the user, it also has a Save function
 */
public class TriviaStats {

    public final static String TRIVIA_STAT1 = "correct";
    public final static String TRIVIA_STAT2 = "incorrect";
    public final static String TRIVIA_STAT3 = "score";

    /**
     * The number of correct answers
     */
    private int correct;

    /**
     * The number of incorrect answers
     */
    private int incorrect;

    /**
     * The score
     */
    private int score;

    /**
     * The arithmetic operation
     */
    private int op;

    /**
     * The difficulty
     */
    private int diff;

    /**
     * The user's username
     */
    private String username;


    /**
     * Constructor for a new game
     *
     * @param username the user's username
     * @param op       the arithmetic operation
     * @param diff     the game difficulty
     */
    TriviaStats(String username, int op, int diff) {
        this.correct = 0;
        this.incorrect = 0;
        this.score = 0;
        this.op = op;
        this.diff = diff;
        this.username = username;
    }


    /**
     * Constructor for a new game
     *
     * @param username  the user's username
     * @param op        the arithmetic operation
     * @param diff      the game difficulty
     * @param correct   the number of correct answers
     * @param incorrect the number of incorrect answers
     */
    TriviaStats(String username, int op, int diff, int correct, int incorrect) {
        this.correct = correct;
        this.incorrect = incorrect;
        this.op = op;
        this.diff = diff;
        this.score = (correct * op * diff * 10) - (incorrect * 5);
        this.username = username;
    }


    /**
     * Returns the game statistics and preferences of the user in a space-separated string
     */
    String saveGame() {
        return this.op + " " + this.diff + " " + this.correct + " " + this.incorrect;
    }


    /**
     * Saves the data to the database
     */
    void saveToDatabase(Context context) {
        DatabaseHelper db = new DatabaseHelper(context);
        long newRowId = db.insertTriviaStats(username, correct, incorrect, score);
        Log.i("TriviaPresenter", "Stats inserted at row" + newRowId);
    }


    /**
     * Updates the points based on whether the question currently answered was correct or not,
     * awarding more by difficulty and operation (hard > medium > easy and mult > subtraction > addition)
     */
    void updatePoints(boolean correct) {
        if (correct) {
            this.score += 10 * this.diff * this.op;
            this.correct++;
        } else {
            this.score -= 5;
            this.incorrect++;
        }
    }

    /**
     * gets displayable (essential) statistics that the user will see on the end screen.
     */

    int[] getStats() {
        return new int[]{correct, incorrect, score};
    }

}
