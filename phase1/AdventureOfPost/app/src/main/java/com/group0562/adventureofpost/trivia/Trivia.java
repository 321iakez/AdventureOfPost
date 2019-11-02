package com.group0562.adventureofpost.trivia;

import com.group0562.adventureofpost.Puzzles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Trivia extends Puzzles {
    private int PuzzlesSolved;
    private ArrayList<Question> questions;
    private ArrayList<Question> rndQuestions;
    private int correct;
    private int incorrect;
    private int score;
    //TODO gonna change later

    /*
    Constructor for Trivia class
     */
    public Trivia() throws IOException {
        super(new TriviaStats(30));
        PuzzlesSolved = 0;
        //TODO These are temporary stats and will change as group work on universal stats
        correct = 0;
        incorrect = 0;
        score = 0;
        //TODO Once pullQuestions() is fixed uncomment line below
        //questions = pullQuestions();

        //Since some error with file reader using hardcoded questions for phase 1
        //create a new arraylist of questions and hardcode questions and answers (will use txt file for phase 2)
        rndQuestions = new ArrayList<>();
        rndQuestions.add(new Question("What does Paul Gries smell like?;;Cinnamon;;Nothing;;Milk;;Lavendar;;Nothing"));
        rndQuestions.add(new Question("Which program has the highest domestic tuition?;;Economics;;Computer Science;;Engineering;;Commerce;;Engineering"));
        rndQuestions.add(new Question("What was the Computer Science POSt cutoff for the 2018-19 year?;;82.5;;85;;89.5;;86;;82.5"));
    }

    //getter for the 3 stats of this puzzle
    public int[] getStats() {
        return new int[]{correct, incorrect, score};
    }

    //returns true if and only if the user selected the correct answer, using question.checkCorrect()
    private boolean checkCorrect(int n) {
        return rndQuestions.get(PuzzlesSolved - 1).checkCorrect(rndQuestions.get(PuzzlesSolved - 1).getOptions()[n]);
    }

    //updates statistics based on user's correctness
    public void updatePoints(int n) {
        if (checkCorrect(n)) {
            this.score += 10;
            this.correct += 1;
        } else {
            this.score -= 5;
            this.incorrect += 1;
        }
    }

    /*
        Reads File "Question.txt" and generates ArrayList of type Questions with contents of file
         */
    //TODO IN PHASE 2, CURRENTLY UNABLE TO FIND QUESTIONS.TXT FILE
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

    //checks whether user has finished 3 questions, returns true if user has completed 2 or less puzzles
    public boolean hasNext() {
        return PuzzlesSolved != 3;
    }

    //gets questions from questions array based on number of questions answered
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

    //TODO: This is just temp, need to change for actual check complete condiditons
    @Override
    public void checkComplete() {
    }
}
