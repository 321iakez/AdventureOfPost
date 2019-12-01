package com.group0562.adventureofpost.sudoku.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.group0562.adventureofpost.R;

public class SudokuScoreboardActivity extends AppCompatActivity {

    private boolean perPlayer;
    private LinearLayout canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_scoreboard);

        perPlayer = getIntent().getBooleanExtra("perPlayer", true);
        canvas = findViewById(R.id.score_board_canvas);

        TextView band = findViewById(R.id.score_board_band);
        if(!perPlayer) {
            band.setText(R.string.global_score_band);
            displayGlobalScore();
        } else
            displayPlayerScore();
    }

    private void displayPlayerScore() {
//        Integer[] playerScorers = scoreboard.
//                getPlayerScore(globalCenter.getCurrentPlayer().getUsername());
//        for (int x = 0; x < playerScorers.length; x++) {
//            scoreColumnView((x+1)+"",
//                    globalCenter.getCurrentPlayer().getUsername(),
//                    playerScorers[x] == null || playerScorers[x] == 0 ? "-":playerScorers[x]+"");

//        }
    }

    private void displayGlobalScore() {

    }
}
