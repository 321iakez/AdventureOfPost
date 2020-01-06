package com.group0562.adventureofpost.trivia;

import android.content.Context;

import com.group0562.adventureofpost.database.DatabaseHelper;

/**
 * This is the presenter class for Trivia, it handles all the
 * interactions with the Trivia object and TriviaStats class
 */
public class TriviaPresenter {

    /**
     * An instance of the trivia game
     */
    private Trivia game;

    /**
     * An instance of TriviaStates which keeps track of user's game statistics
     */
    private TriviaStats gameStats;

    /**
     * The background color of the game
     */
    private String backgroundColor;

    /**
     * The color of the text in the game
     */
    private String textColor;

    /**
     * The color of the buttons in the game
     */
    private String buttonColor;


    /**
     * The constructor for a new game
     *
     * @param username the username of the player
     * @param op       the arithmetic operation
     * @param diff     the difficulty
     */
    public TriviaPresenter(String username, int op, int diff) {
        backgroundColor = "Yellow";
        textColor = "Black";
        buttonColor = "Pink";
        game = new Trivia(op, diff);
        gameStats = new TriviaStats(username, op, diff);
    }

    /**
     * The constructor for a saved game
     *
     * @param username  the username of the player
     * @param saveState a String of saved info containing user's game progress and customization
     */
    public TriviaPresenter(String username, String saveState) {
        super();
        loadGame(username, saveState);
    }

    /**
     * Calls saveToDatabase method in triviaStats
     */
    public void saveToDatabase(Context context) {
        gameStats.saveToDatabase(context);
    }

    /**
     * Handles when the user clicks a button and checks if correct
     *
     * @param n the option that the user clicked
     */
    public void onClick(int n) {
        boolean correct = game.checkCorrect(n);
        gameStats.updatePoints(correct);
    }

    /**
     * Calls trivia getQuestion to retrieve the next question
     */
    public Question getQuestion() {
        return game.getQuestion();
    }

    /**
     * Calls trivia hasNext to check if trivia has another question
     */
    public boolean hasNext() {
        return game.hasNext();
    }

    /**
     * Calls triviaStats saveGame to get game info, combines with customization info and returns
     */
    public String saveGame() {
        String colorData = this.backgroundColor + " " + this.textColor + " " + this.buttonColor;
        return gameStats.saveGame() + " " + colorData;// ADD THIS WHEN FRONTEND IMPLEMENTED
    }


    /**
     * Sets the trivia and trivia stats to a user's current saved game
     *
     * @param username  the user's username
     * @param saveState A string of game information and customization information
     */
    private void loadGame(String username, String saveState) {
        //decode string
        String[] userGameInfo = saveState.split(" ");

        int op = Integer.parseInt(userGameInfo[0]);
        int diff = Integer.parseInt(userGameInfo[1]);
        int correct = Integer.parseInt(userGameInfo[2]);
        int incorrect = Integer.parseInt(userGameInfo[3]);
        this.backgroundColor = userGameInfo[4];
        this.textColor = userGameInfo[5];
        this.buttonColor = userGameInfo[6];
        this.gameStats = new TriviaStats(username, op, diff, correct, incorrect);
        this.game = new Trivia(op, diff, correct + incorrect);
    }

    /**
     * stores a saved state to the database indexed to the username
     *
     * @param saveState a string containing vital gameinfo data
     * @param username  the user's username
     */
    public void insertToDatabase(Context context, String saveState, String username) {
        DatabaseHelper db = new DatabaseHelper(context);
        db.insertGameState("trivia", username, saveState);
    }


    /**
     * loads a saved state to the database indexed to the username
     *
     * @param username the user's username
     */
    public void loadFromDatabase(Context context, String username) {
        DatabaseHelper db = new DatabaseHelper(context);
        String saveState = db.retrieveGameState("trivia", username);
        if (!(saveState.isEmpty())) {
            loadGame(username, saveState);
        }
    }

    /**
     * Sets the background color
     *
     * @param color the color
     */
    public void setBackgroundColor(String color) {
        this.backgroundColor = color;
    }

    /**
     * Gets the background color
     */
    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    /**
     * Sets the button color
     *
     * @param color the color
     */
    public void setButtonColor(String color) {
        this.buttonColor = color;
    }

    /**
     * Gets the button color
     */
    public String getButtonColor() {
        return this.buttonColor;
    }

    /**
     * Sets the text color
     *
     * @param color the color
     */
    public void setTextColor(String color) {
        this.textColor = color;
    }

    /**
     * Gets the text color
     */
    public String getTextColor() {
        return this.textColor;
    }

    /**
     * Gets the current game statistics from gameStats
     */
    public int[] getStats() {
        return gameStats.getStats();
    }


}
