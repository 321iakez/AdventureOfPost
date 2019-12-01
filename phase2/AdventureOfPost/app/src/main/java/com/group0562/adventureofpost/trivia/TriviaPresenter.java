package com.group0562.adventureofpost.trivia;


public class TriviaPresenter{

    private Trivia game;
    private TriviaStats gameStats;
    private String color;


    public TriviaPresenter(String username, int op, int diff) {
        color  = "White";
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

    public void setColor(String color){this.color = color;}

    public String getColor(){return this.color;}

    public int[] getStats() {
        return gameStats.getStats();
    }



}
