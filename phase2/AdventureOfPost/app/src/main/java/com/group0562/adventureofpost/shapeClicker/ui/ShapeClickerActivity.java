package com.group0562.adventureofpost.shapeClicker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.group0562.adventureofpost.GameActivity;
import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.database.DatabaseHelper;

/* this is the main page of the game that controls most of the things */
public class ShapeClickerActivity extends AppCompatActivity implements SCPauseDialog.SCPauseDialogListener {
    Button sc_done;
    ShapeClickerGameView sc_view;

    private final String RETURN_NO_SAVE = "RETURN_NO_SAVE";
    private final String RETURN_SAVE = "RETURN_SAVE";
    private long time_pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_clicker);
        String username = getIntent().getStringExtra("username");
        String resume = getIntent().getStringExtra("Resume");
        DatabaseHelper db = new DatabaseHelper(this);
        String gameState = db.retrieveGameState("shapeClicker", username);
        sc_view = findViewById(R.id.scview);
        if (!gameState.equals("") && resume.equals("yes")) {
            sc_view.getClicker().getPuzzleStats().setLoadData(gameState);
        }
    }

    /*to start the setting screen once clicked*/
    public void onClickSCExit(View view) {
        SCPauseDialog pauseDialog = new SCPauseDialog();
        pauseDialog.show(getSupportFragmentManager(), "pause dialog");
        time_pause = sc_view.puzzleStats.getTime();
    }

    /*to start the finish screen once clicked*/
    public void onClickSCDone(View view) {
        Intent intent = new Intent(this, ShapeClickerEndActivity.class);
        sc_done = findViewById(R.id.sc_finish_button);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        intent.putExtra("data", sc_view.getClicker().getPuzzleStats().getSCData());
        startActivity(intent);
    }

    /**Implement the saveGame method to decide what to do when each choice is called */
    @Override
    public void saveGame(String mode) {
        if (mode.equals(RETURN_NO_SAVE)) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);

        } else if (mode.equals(RETURN_SAVE)) {
            sc_view.getClicker().saveShapeClicker(this);
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);
        } else {
            sc_view.puzzleStats.setTime(time_pause);
        }
    }
}
