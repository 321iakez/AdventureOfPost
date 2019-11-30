package com.group0562.adventureofpost.trivia;

import android.view.View;

import com.group0562.adventureofpost.AdventureOfPost;
import com.group0562.adventureofpost.Puzzles;

public class TriviaPresenter extends Puzzles {

    private Trivia game;
    private TriviaView view;
    private TriviaStats gameStats;

    public TriviaPresenter(TriviaView view, int op, int diff) {
        super();
        this.view = view;
        game = new Trivia(op, diff);
        gameStats = new TriviaStats(op, diff);

    }


    @Override
    public void checkComplete() {
        if(game.checkComplete())
            setPuzzleComplete(true);
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

    @Override
    public void update() {
        view.updateStats();

        // Check complete
        checkComplete();
        if (getPuzzleComplete()) {
            onStop();
        }
    }

    public String saveGame() {
        return gameStats.saveGame();
    }

    public void loadGame(String saveState) {
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

    @Override
    public void onStop() {
        view.onGameComplete();
    }


}
