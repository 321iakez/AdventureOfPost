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
    private int diff;

    /**
     * the gamemode in terms of operations, of the game. 1 means addition, 2 means subtraction,
     * 3 means multiplication
     */
    private int op;

    /**
     * Constructor for trivia class
     */
    public Trivia(){
        super(new TriviaStats(30));
        PuzzlesSolved = 0;
        diff = 1;
        op = 1;
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
            this.score += 10 * this.diff * this.op;
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
            //TODO gameType is hardcoded right now will change for more options later
            list.add(new Question(this.diff, this.op));
        }
        return list;
    }

    public void setDiff(int diff) {
        this.diff = diff;
        questions = genQuestions();
    }

    public void setOperation(int op){
        this.op = op;
    }

    private void setGame(int diff, int op, int correct, int incorrect){

        this.correct = correct;
        this.incorrect = incorrect;
        this.PuzzlesSolved = correct + incorrect;
        this.score = this.correct * 10 - this.incorrect * 5;
        this.op = op;
        this.diff = diff;

    }

    /**
     * This method will take in a String saveState and decode it to load all the information
     * @param saveState the save state of the game
     *saveState string in this structure:
     */
    public void loadGame(String saveState){
        //this is temp
        try{
            String[] gameInfo = saveState.split(" ");
            this.diff = Integer.parseInt(gameInfo[0]);
            this.op = Integer.parseInt(gameInfo[1]);
            this.correct = Integer.parseInt(gameInfo[2]);
            this.incorrect = Integer.parseInt(gameInfo[3]);
            this.PuzzlesSolved = this.correct + this.incorrect + 1;

        }
        catch(Exception e){

            System.out.println("Not a valid saveState string");
        }
    }

    //TODO this will later include all game info like completion
    /**
     * Returns a String of the current stats of the game
     * stats contains difficulty, correct, incorrect
     * To be store in a database such that this data can be used to resume a game
     */
    public String saveGame(){
        return Integer.toString(this.diff) + " " + Integer.toString(this.op) + " " + Integer.toString(this.correct) + " " + Integer.toString(this.incorrect);

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

    @Override
    public void checkComplete() {
    }
}
