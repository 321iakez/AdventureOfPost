package com.group0562.adventureofpost.trivia;

import com.group0562.adventureofpost.Puzzles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Trivia {

    /**
     * the number of puzzles that the user has currently solved
     */
    private int PuzzlesSolved;

    /**
     * An arrayList of all the possible questions that can be asked to be used in phase 2
     */
     private ArrayList<Question> questions;


    public Trivia(int op, int diff){
        genQuestions(op, diff);
        PuzzlesSolved = 0;


    }


    /**
     * returns true iff the user selected the correct answer, using question.checkCorrect()
     * @param n the option number
     */
    boolean checkCorrect(int n) {
        return questions.get(PuzzlesSolved - 1).checkCorrect(questions.get(PuzzlesSolved - 1).getOptions()[n]);
    }

    /**
     * Generates 10 random questions
     */
    private ArrayList<Question> genQuestions(int op, int diff) {
        ArrayList<Question> list = new ArrayList<>();

        for (int i = 1; i <= 10 ; i++){
            //TODO gameType is hardcoded right now will change for more options later
            list.add(new Question(diff, op));
        }
        return list;
    }

    /**
     * This method will take in a String saveState and decode it to load all the information
     * @param saveState the save state of the game
     *saveState string in this structure: (difficulty operation correct incorrect) separated by spaces
     */
    /*
    public void loadGame(String saveState){
        //this is temp
        try{
            String[] gameInfo = saveState.split(" ");
            this.diff = Integer.parseInt(gameInfo[0]);
            this.op = Integer.parseInt(gameInfo[1]);
            //this.correct = Integer.parseInt(gameInfo[2]);
            //this.incorrect = Integer.parseInt(gameInfo[3]);
            //this.PuzzlesSolved = this.correct + this.incorrect + 1;

        }
        catch(Exception e){

            System.out.println("Not a valid saveState string");
        }
    }
     */

    //TODO this will later include all game info like completion
    /**
     * Returns a String of the current stats of the game
     * stats contains difficulty, correct, incorrect
     * To be store in a database such that this data can be used to resume a game
     */
    //public String saveGame(){
        //return this.diff + " " + this.op + " " + this.correct + " " + this.incorrect;
    //}

    /**
     * Checks whether user has finished 3 questions, returns true iff user has completed <= 2 puzzles
     */
    boolean hasNext() {
        return PuzzlesSolved != questions.size();
    }

    /**
     * Gets questions from question array based on number of questions answered
     */
    Question getQuestion() {
        return questions.get(PuzzlesSolved++);
    }

    boolean checkComplete() {
        return PuzzlesSolved == questions.size();
    }
}
