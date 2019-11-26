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

    private String gameType;

    /**
     * Question constructor initializes variables by processing input string content
    TODO Constructor and formatting of questions.txt need to change to implement shuffle
    * @param diff the string for the difficulty
     *
    */
    Question(String diff) {
        int number1, number2, bound, answer, option;
        Random rand = new Random();

        this.gameType = "add";

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
        String[] tempQuestions;
        switch (this.gameType){

            case "add":
                tempQuestions = createAdditionQuestion(diff, bound);
                break;
            case "sub":
                tempQuestions = createSubtractionQuestion(diff, bound);
                break;
            case "mult":
                tempQuestions = createMultiplicationQuestion(diff, bound);
             default:
                 tempQuestions = createMultiplicationQuestion(diff, bound);
                 break;

        }

        question = tempQuestions[0];
        this.answer = tempQuestions[1];

        for (int i = 0; i <= 3; i++){
            option = Integer.parseInt(this.answer) + rand.nextInt(bound) - rand.nextInt(bound);
            if (option != Integer.parseInt(this.answer)) {
                options[i] = Integer.toString(option);
            } else {
                i--;
            }
        }
        options[3] = this.answer;
    }

    public String[] createAdditionQuestion(String diff, int bound)
    {
        Random random = new Random();
        int number1 = random.nextInt(bound);
        int number2 = random.nextInt(bound);
        String[] info = new String[5];
        info[0] = number1 + " + " + number2 + "=? ";
        info[1] = Integer.toString(number1 + number2);


    return info;
    }

    public String[] createSubtractionQuestion(String diff, int bound)
    {
        Random random = new Random();
        int number1 = random.nextInt(bound);
        int number2 = random.nextInt(bound);
        String[] info = new String[5];
        info[0] = number1 + " - " + number2 + "=? ";
        info[1] = Integer.toString(number1 - number2);


        return info;
    }

    public String[] createMultiplicationQuestion(String diff, int bound)
    {
        Random random = new Random();
        int number1 = random.nextInt(bound);
        int number2 = random.nextInt(bound);
        String[] info = new String[5];
        info[0] = number1 + " x " + number2 + "=? ";
        info[1] = Integer.toString(number1 * number2);


        return info;
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
