package com.group0562.adventureofpost.trivia;

import com.group0562.adventureofpost.AdventureOfPost;

import java.io.Serializable;
import java.util.Observable;

public class TriviaStats implements Serializable {

    private int puzzlesSolved;
    private int correct;
    private int incorrect;
    private int score;
    private int op;
    private int diff;

    TriviaStats(int op, int diff) {
        this.correct = 0;
        this.incorrect = 0;
        this.score = 0;
        this.puzzlesSolved = 0;
        this.op = op;
        this.diff = diff;
    }

    TriviaStats(int op, int diff, int correct, int incorrect){
        this.correct = correct;
        this.incorrect = incorrect;
        this.op = op;
        this.diff = diff;
        this.puzzlesSolved = correct + incorrect;
        this.score = (correct * op * diff * 10) - (incorrect * 5);

    }

    public String saveGame(){
        return this.op + " " + this.diff + " " + this.correct + " " + this.incorrect;
    }


    public void updatePoints(boolean correct) {
        if (correct){
            this.score += 10 * this.diff * this.op;
        }
        else{
            this.score -= 5;
        }
    }

}
