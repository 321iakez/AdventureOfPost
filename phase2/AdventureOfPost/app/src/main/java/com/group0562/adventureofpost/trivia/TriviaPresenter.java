package com.group0562.adventureofpost.trivia;


public class TriviaPresenter{

    private Trivia game;
    private TriviaStats gameStats;

    public TriviaPresenter(int op, int diff) {
        game = new Trivia(op, diff);
        gameStats = new TriviaStats(op, diff);
    }

    public TriviaPresenter(String saveState) {
        super();
        loadGame(saveState);
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

    private void loadGame(String saveState) {
        //decode string
        int op = 1;
        int diff = 1;
        int correct = 1;
        int incorrect = 1;
        this.gameStats = new TriviaStats(op, diff, correct, incorrect);
        this.game = new Trivia(op, diff, correct + incorrect);
    }

    public int[] getStats() {
        return gameStats.getStats();
    }



}
