package com.group0562.adventureofpost.trivia;


public class TriviaPresenter{

    private Trivia game;
    private TriviaStats gameStats;
    private String backgroundColor;
    private String textColor;
    private String buttonColor;



    public TriviaPresenter(String username, int op, int diff) {
        backgroundColor  = "White";
        textColor = "Black";
        buttonColor = "Yellow";
        game = new Trivia(op, diff);
        gameStats = new TriviaStats(username, op, diff);
    }

    public TriviaPresenter(String username, String saveState) {
        super();
        loadGame(username, saveState);
    }


    public void onClick(int n){
        boolean correct = game.checkCorrect(n);
        gameStats.updatePoints(correct);
    }

    public Question getQuestion(){
        return game.getQuestion();
    }

    public boolean hasNext() {
        return game.hasNext();
    }


    public String saveGame() {
        return gameStats.saveGame();
    }

    private void loadGame(String username, String saveState) {
        //decode string
        String[] userGameInfo = saveState.split(" ");

        int op = Integer.parseInt(userGameInfo[0]);
        int diff = Integer.parseInt(userGameInfo[1]);
        int correct = Integer.parseInt(userGameInfo[2]);
        int incorrect = Integer.parseInt(userGameInfo[3]);

        this.gameStats = new TriviaStats(username, op, diff, correct, incorrect);
        this.game = new Trivia(op, diff, correct + incorrect);
    }

    public void setBackgroundColor(String color){this.backgroundColor = color;}

    public String getBackgroundColor(){return this.backgroundColor;}

    public void setButtonColor(String color){this.buttonColor = color;}

    public String getButtonColor(){return this.buttonColor;}

    public void setTextColor(String color){this.textColor = color;}

    public String getTextColor(){return this.textColor;}

    public int[] getStats() {
        return gameStats.getStats();
    }



}
