package com.group0562.adventureofpost.trivia;

import java.util.Random;

public class Question {

    //String question stores the text of the question
    private String question;

    //String answer stores the text of the answer to the question
    private String answer;

    //Array options of length 4 stores the possible options, including one correct and 3 incorrect
    private String[] options = new String[4];

    //TODO Constructor and formatting of questions.txt need to change to implement shuffle
    Question(String content) {
        String[] splitContent = content.split(";;");
        this.question = splitContent[0];
        if (splitContent.length - 1 - 1 >= 0)
            System.arraycopy(splitContent, 1, this.options, 0, splitContent.length - 1 - 1);
        this.answer = splitContent[5];
    }

    //getter for question
    public String getQuestion() {
        return this.question;
    }

    //checks whether a user's response is equal to the answer
    boolean checkCorrect(String response) {
        return response.equals(this.answer);
    }

    //returns the four possible answers to the question
    public String[] getOptions() {
        //shuffleOptions(options);
        return this.options;
    }

    //shuffles the array of options, use this for phase 2
    private void shuffleOptions(String[] options) {
        int n = options.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            String helper = options[i];
            options[i] = options[change];
            options[change] = helper;
        }

    }


}
