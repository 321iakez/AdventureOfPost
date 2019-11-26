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
     * private ArrayList<Question> questions;
     */


    /**
     * An arrayList of 3 randomly selected questions (Currently not randomly selected, to be random in phase 2)
     */
    private ArrayList<Question> rndQuestions;

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
        //questions = pullQuestions();

        /*
        * Since some error with file reader using hardcoded questions for phase 1
        * create a new arrayList of questions and hardcode questions and answers (will use txt file for phase 2)
        */
        rndQuestions = new ArrayList<>();
        rndQuestions.add(new Question("What does Paul Gries smell like?;;Cinnamon;;Nothing;;Milk;;Lavendar;;Nothing"));
        rndQuestions.add(new Question("Which program has the highest domestic tuition?;;Economics;;Computer Science;;Engineering;;Commerce;;Engineering"));
        rndQuestions.add(new Question("What was the Computer Science POSt cutoff for the 2018-19 year?;;82.5;;85;;89.5;;86;;82.5"));
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
        return rndQuestions.get(PuzzlesSolved - 1).checkCorrect(rndQuestions.get(PuzzlesSolved - 1).getOptions()[n]);
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

    public void setDiff(String diff) {
        this.diff = diff;
    }

    /**
        Reads File "Question.txt" and generates ArrayList of type Questions with contents of file
         */
    //TODO IN PHASE 2, CURRENTLY UNABLE TO FIND QUESTIONS.TXT FILE
    /*
    private ArrayList<Question> pullQuestions() throws IOException {
        FileReader fr = new FileReader("Questions.txt");
        BufferedReader br = new BufferedReader(fr);
        String currLine = br.readLine();
        ArrayList<Question> questions = new ArrayList<>();
        while (currLine != null) {
            questions.add(new Question(currLine));
            currLine = br.readLine();
        }
        return questions;
    }
    */

    /**
     * Checks whether user has finished 3 questions, returns true iff user has completed <= 2 puzzles
     */
    public boolean hasNext() {
        return PuzzlesSolved != 3;
    }

    /**
     * Gets questions from question array based on number of questions answered
     */
    public Question getQuestion() {
        return rndQuestions.get(PuzzlesSolved++);
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
