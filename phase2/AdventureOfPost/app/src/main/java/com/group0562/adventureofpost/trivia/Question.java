package com.group0562.adventureofpost.trivia;

import java.io.Serializable;
import java.util.Random;

public class Question implements Serializable {

    /**
    * String question stores the text of the question
    */
    private String question;

    /**
    * String answer stores the text of the answer to the question
    */
    private String answer;

    /**
    * Array options of length 4 stores the possible options, including one correct and 3 incorrect
     */
    private String[] options = new String[4];

    /**
     * Question constructor initializes variables by processing input string content
    TODO Constructor and formatting of questions.txt need to change to implement shuffle
    * @param diff the string for the difficulty
     *
    */
    Question(String diff) {
        int number1, number2, bound, answer, option;
        Random random = new Random();

        switch (diff) {
            case "easy":
                bound = 10;
                break;
            case "medium":
                bound = 100;
                break;
            case "hard":
                bound = 1000;
                break;
            default:
                bound = 0;
                break;
        }

        number1 = random.nextInt(bound);
        number2 = random.nextInt(bound);
        question = number1 + " + " + number2 + "= ? ";
        answer = number1 + number2;

        this.answer = Integer.toString(answer);

        for (int i = 0; i <= 3; i++){
            option = random.nextInt(2*bound);
            if (option != answer) {
                options[i] = Integer.toString(option);
            } else {
                i--;
            }
        }
        options[3] =Integer.toString(answer);
    }

    /**
     * Getter for question
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     * Checks whether a user response is equal to the answer, returning true if so
     * @param response the user's answer
     */
    boolean checkCorrect(String response) {
        return Integer.parseInt(response) == Integer.parseInt(answer);
    }

    /**
     * Returns the four possible options for answers to be displayed
     */
    public String[] getOptions() {
        //shuffleOptions(options);
        return this.options;
    }

    /**
     * Shuffles array of options, to be used in phase 2
     */
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
