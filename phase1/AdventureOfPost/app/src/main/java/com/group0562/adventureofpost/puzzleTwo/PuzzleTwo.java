package com.group0562.adventureofpost.puzzleTwo;

import com.group0562.adventureofpost.Puzzles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PuzzleTwo extends Puzzles {
    private int PuzzlesSolved;
    private ArrayList<Question> questions;

    /*
    Constructor for PuzzleTwo class
     */
    public PuzzleTwo() throws IOException{
        super(new PuzzleTwoStats(30));
        PuzzlesSolved = 0;
        questions = pullQuestions();
    }


    /*
    Reads File "Question.txt" and generates ArrayList of type Questions with contents of file
     */
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

    public void update() {
        //String[] options = ;
//        updateTime();
//        updatePoints();
        checkComplete();


        //TODO have code that gets input form frontend (see if user tapped anything)


        //checks if the user has completed 3 puzzles, allow them to end game if true
        if (PuzzlesSolved == 3) {
            setPuzzleComplete(true);
        }

        if (puzzleStats.getTime() == 0 | getPuzzleComplete()) {
            onStop();
        }
        update();
    }

//    @Override
//    public void updatePoints() {
//
//    }

    //TODO: This is just temp, need to change for actual check complete condiditons
    @Override
    public void checkComplete() {
    }
}
