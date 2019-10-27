package com.group0562.adventureofpost.puzzleTwo;

public class Question {
    private String question;
    private String answer;
    private String[] options = new String[4];

    //TODO Constructer and formatting of questions.txt need to change to implement shuffle
    Question (String content) {
        String[] splitContent = content.split(";;");
        this.question = splitContent[0];
        for (int i = 1 ; i < splitContent.length - 1 ; i ++) {
            this.options[i - 1] = splitContent[i];
        }
        this.answer = splitContent[5];
    }

    String getQuestion() {
        return this.question;
    }

    boolean checkCorrect(String response) {
        if (response.equals(this.answer)){
            return true;
        } else {
            return false;
        }
    }

    //TODO this will be changed later
    String[] getOptions() {
        return this.options;
    }

    //TODO generate shuffled options array
    /*
    String [] getShufffledOptions() {

    }
     */
}
