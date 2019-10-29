package com.group0562.adventureofpost.trivia;

import java.util.Random;

public class Question {
    private String question;
    private String answer;
    private String[] options = new String[4];

    //TODO Constructor and formatting of questions.txt need to change to implement shuffle
    Question(String content) {
        String[] splitContent = content.split(";;");
        this.question = splitContent[0];
        for (int i = 1; i < splitContent.length - 1; i++) {
            this.options[i - 1] = splitContent[i];
        }
        this.answer = splitContent[5];
    }

    public String getQuestion() {
        return this.question;
    }

    boolean checkCorrect(String response) {
        if (response.equals(this.answer)) {
            return true;
        } else {
            return false;
        }
    }

    public String[] getOptions() {
        return this.options;
    }

    void shuffleOptions(String[] options) {
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
