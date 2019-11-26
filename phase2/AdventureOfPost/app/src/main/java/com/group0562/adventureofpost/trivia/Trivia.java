package com.group0562.adventureofpost.trivia;

import com.group0562.adventureofpost.Puzzles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Trivia extends Puzzles implements Serializable {

    /**
     * the number of puzzles that the user has currently solved
     */
    private int PuzzlesSolved;

    /**
     * An arrayList of all the possible questions that can be asked to be used in phase 2
     */
     private ArrayList<Question> questions;

    /**
     * number of correct responses
     */
    private int correct;

    /**
     * Number of incorrect responses
     */
    private int incorrect;

    /**
     * The player's score, +10 for each correct answer and -5 for each incorrect answer
     */
    private int score;
    //TODO remove score for phase two and calculate as a function of correct/incorrect and time

    /**
     * difficulty of game
     */
    private String diff;

    /**
     * Constructor for trivia class
     */
    public Trivia(){
        super(new TriviaStats(30));
        PuzzlesSolved = 0;
        diff = "easy";
        //TODO These are temporary stats and will change as group work on universal stats
        correct = 0;
        incorrect = 0;
        score = 0;
        //TODO Once pullQuestions() is fixed uncomment line below

    }

    /**
     * returns the user's stats in an array of length 3
     */
    public int[] getStats() {
        return new int[]{correct, incorrect, score};
    }

    /**
     * returns true iff the user selected the correct answer, using question.checkCorrect()
     * @param n the option number
     */
    private boolean checkCorrect(int n) {
        return questions.get(PuzzlesSolved - 1).checkCorrect(questions.get(PuzzlesSolved - 1).getOptions()[n]);
    }
    /**
     * updates statistics based on user's correctness
     * @param n the option number
     */
    public void updatePoints(int n) {
        if (checkCorrect(n)) {
            this.score += 10;
            this.correct += 1;
        } else {
            this.score -= 5;
            this.incorrect += 1;
        }
    }

    /**
     * Generates 10 random questions
     */
    private ArrayList<Question> genQuestions() {
        ArrayList<Question> list = new ArrayList<>();

        for (int i = 1; i <= 10 ; i++){
            list.add(new Question(this.diff));
        }
        return list;
    }

    public void setDiff(String diff) {
        this.diff = diff;
        questions = genQuestions();
    }

    /**
     * Checks whether user has finished 3 questions, returns true iff user has completed <= 2 puzzles
     */
    public boolean hasNext() {
        return PuzzlesSolved != questions.size();
    }

    /**
     * Gets questions from question array based on number of questions answered
     */
    public Question getQuestion() {
        return questions.get(PuzzlesSolved++);
    }

    /* TODO later
    public void update() {
        String[] options = ;
        updateTime();
        updatePoints();
        checkComplete();


        //TODO have code that gets input form frontend (see if user tapped anything)


        //checks if the user has completed 3 puzzles, allow them to end game if true
        if (PuzzlesSolved == 3) {
            setPuzzleComplete(true);
        }

        if (puzzleStats.getTime() == 0 | getPuzzleComplete()) {
            onStop();
        }
    }

    @Override
    public void updatePoints() {

    }

     */

    @Override
    public void checkComplete() {
    }
}
