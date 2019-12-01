package com.group0562.adventureofpost.shapeClicker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.group0562.adventureofpost.GameActivity;
import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.shapeClicker.ShapeClickerGameView;

public class ShapeClickerActivity extends AppCompatActivity implements SCPauseDialog.SCPauseDialogListener {
    public final static String EXTRA_MESSAGE = "com.group0562.AdventureOfPost.MESSAGE";
    Button sc_done;
    ShapeClickerGameView sc_view;

    private final String RETURN_NO_SAVE = "RETURN_NO_SAVE";
    private final String RETURN_SAVE = "RETURN_SAVE";
    private final String RESUME = "RESUME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_clicker);
        //String username = getIntent().getStringExtra("username");
        //SCSetting.setUsername(username);
        sc_view = findViewById(R.id.scview);
    }

    /*to start the setting screen once clicked*/
    public void onClickSCExit(View view) {
        SCPauseDialog pauseDialog = new SCPauseDialog();
        pauseDialog.show(getSupportFragmentManager(), "pause dialog");
    }

    /*to start the finish screen once clicked*/
    public void onClickSCDone(View view) {
        Intent intent = new Intent(this, ShapeClickerEndActivity.class);
        sc_done = findViewById(R.id.sc_finish_button);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }

    @Override
    public void saveGame(String mode) {
        if (mode.equals(RETURN_NO_SAVE)) {
            System.out.println("returned without save");
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);

        } else if (mode.equals(RETURN_SAVE)) {
            System.out.println("returned with save");
            sc_view.getClicker().saveStats(this);
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);
        } else {
            System.out.println("Resumed");
        }
    }
}
