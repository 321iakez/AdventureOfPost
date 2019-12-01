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
     * the question is randomly generated based on difficulty and operation
    * @param diff the string for the difficulty
     * @param op the arithmetic operation
     *
    */
    Question(int diff, int op) {
        int bound, option;
        Random rand = new Random();

        switch (diff) {
            case 1:
                bound = 10;
                break;
            case 2:
                bound = 100;
                break;
            case 3:
                bound = 1000;
                break;
            default:
                bound = 0;
                break;
        }
        String[] tempQuestions;
        switch (op) {

            case 1:
                tempQuestions = createAdditionQuestion(bound);
                break;
            case 2:
                tempQuestions = createSubtractionQuestion(bound);
                break;
            default:
                if (bound == 1000) {
                    tempQuestions = createMultiplicationQuestion(bound / 10);
                }
                else if (bound == 100){
                    tempQuestions = createMultiplicationQuestion(bound/5);
                }
                else{
                    tempQuestions = createMultiplicationQuestion(bound);
                }
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
        shuffleOptions(this.options);
    }


    /**
     * Helper function to create an addition question
     * @param bound the upper bound for randomly-generated numbers
     */
    private String[] createAdditionQuestion(int bound)
    {
        Random random = new Random();
        int number1 = random.nextInt(bound);
        int number2 = random.nextInt(bound);
        String[] info = new String[5];
        info[0] = number1 + " + " + number2 + "=? ";
        info[1] = Integer.toString(number1 + number2);


    return info;
    }


    /**
     * Helper to create a subtraction question
     * @param bound the upper bound for randomly-generated numbers
     */
    private String[] createSubtractionQuestion(int bound)
    {
        Random random = new Random();
        int number1 = random.nextInt(bound);
        int number2 = random.nextInt(bound);
        String[] info = new String[5];
        info[0] = number1 + " - " + number2 + "=? ";
        info[1] = Integer.toString(number1 - number2);


        return info;
    }

    /**
     * The helper function to create a multiplication question
     * @param bound the maximum randomly-generated number
     */
    private String[] createMultiplicationQuestion(int bound)
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


    /**
     * Getter for the question
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     * Checks whether a user response is equal to the answer, returning true if so
     * @param response the user's answer
     */
    boolean checkCorrect(String response) {
        return Integer.parseInt(response) == Integer.parseInt(this.answer);
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
