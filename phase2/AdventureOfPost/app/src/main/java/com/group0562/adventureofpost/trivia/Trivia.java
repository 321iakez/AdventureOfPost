package com.group0562.adventureofpost.trivia;

import java.util.ArrayList;

public class Trivia {

    /**
     * the number of puzzles that the user has currently solved
     */
    private int puzzlesSolved;

    /**
     * An arrayList of all the possible questions that can be asked to be used in phase 2
     */
     private ArrayList<Question> questions;


    public Trivia(int op, int diff){
        this.questions = genQuestions(op, diff);
        puzzlesSolved = 1;


    }

    public Trivia(int op, int diff, int puzzlesSolved){
        this.questions = genQuestions(op, diff);
        this.puzzlesSolved = puzzlesSolved;

    }


    /**
     * returns true iff the user selected the correct answer, using question.checkCorrect()
     * @param n the option number
     */
    boolean checkCorrect(int n) {
        return questions.get(puzzlesSolved - 1).checkCorrect(questions.get(puzzlesSolved - 1).getOptions()[n]);
    }

    /**
     * Generates 10 random questions
     */
    private ArrayList<Question> genQuestions(int op, int diff) {
        ArrayList<Question> list = new ArrayList<>();

        for (int i = 1; i <= 10 ; i++){
            list.add(new Question(diff, op));
        }
        return list;
    }


    /**
     * Checks whether user has finished 3 questions, returns true iff user has completed <= 2 puzzles
     */
    boolean hasNext() {
        return puzzlesSolved != questions.size();
    }

    /**
     * Gets questions from question array based on number of questions answered
     */
    Question getQuestion() {
        return questions.get(puzzlesSolved++);
    }
}
