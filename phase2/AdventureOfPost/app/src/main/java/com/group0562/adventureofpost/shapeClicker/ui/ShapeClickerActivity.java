package com.group0562.adventureofpost.shapeClicker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.group0562.adventureofpost.R;

public class ShapeClickerActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.group0562.AdventureOfPost.MESSAGE";
    Button sc_setting;
    Button sc_done;
    Button sc_pause_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_clicker);
    }

    /*to start the setting screen once clicked*/
    public void onClickSCSetting(View view) {
        Intent intent = new Intent(this, SCSettingActivity.class);
        sc_setting = findViewById(R.id.SCSettingButton);
        startActivity(intent);
    }

    /*to start the finish screen once clicked*/
    public void onClickSCDone(View view) {
        Intent intent = new Intent(this, ShapeClickerEndActivity.class);
        sc_done = findViewById(R.id.sc_finish_button);
        startActivity(intent);
    }

    public void onClickSCPause(View view){
        Intent intent = new Intent(this, ShapeClickerPauseContinueActivity.class);
        sc_pause_continue = findViewById(R.id.sc_pc_button);
        startActivity(intent);
    }
}
