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
    //TODO gonna change later

    /*
    Constructor for Trivia class
     */
    public Trivia() throws IOException{
        super(new TriviaStats(30));
        PuzzlesSolved = 0;
        //TODO Once pullQuestions() is fixed uncomment line below
        //questions = pullQuestions();
        rndQuestions = new ArrayList<>();
        rndQuestions.add(new Question("What does Paul Gries smell like?;;Cinnamon;;Nothing;;Milk;;Lavendar;;Nothing"));
        rndQuestions.add(new Question("Which program has the highest domestic tuition?;;Economics;;Computer Science;;Engineering;;Commerce;;Engineering"));
        rndQuestions.add(new Question("What was the Computer Science POSt cutoff for the 2018-19 year?;;82.5;;85;;89.5;;86;;82.5"));
    }


    /*
    Reads File "Question.txt" and generates ArrayList of type Questions with contents of file
     */
    //TODO SOMETHING IS WRONG HERE LOL
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

    public Question getQuestion() {
        if (PuzzlesSolved == 3) {
            PuzzlesSolved = 0;
        }
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
