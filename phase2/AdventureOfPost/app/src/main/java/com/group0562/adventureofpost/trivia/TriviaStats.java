package com.group0562.adventureofpost.trivia;


import android.content.Context;

class TriviaStats {

    public final static String TRIVIA_STAT1 = "correct";
    public final static String TRIVIA_STAT2 = "incorrect";
    public final static String TRIVIA_STAT3 = "score";

    private int correct;
    private int incorrect;
    private int score;
    private int op;
    private int diff;
    private String username;

    TriviaStats(String username, int op, int diff) {
        this.correct = 0;
        this.incorrect = 0;
        this.score = 0;
        this.op = op;
        this.diff = diff;
        this.username = username;
    }

    TriviaStats(String username, int op, int diff, int correct, int incorrect) {
        this.correct = correct;
        this.incorrect = incorrect;
        this.op = op;
        this.diff = diff;
        this.score = (correct * op * diff * 10) - (incorrect * 5);
        this.username = username;
    }

    String saveGame() {
        return this.op + " " + this.diff + " " + this.correct + " " + this.incorrect;
    }

    void saveToDatabase(Context context){

    }


    void updatePoints(boolean correct) {
        if (correct) {
            this.score += 10 * this.diff * this.op;
            this.correct++;
        } else {
            this.score -= 5;
            this.incorrect++;
        }
    }

    int[] getStats() {
        return new int[]{correct, incorrect, score};
    }

}
