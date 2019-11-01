package com.group0562.adventureofpost.shapeClicker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group0562.adventureofpost.R;

public class ShapeClickerActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.group0562.AdventureOfPost.MESSAGE";
    Button sc_setting;
    Button sc_done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_clicker);
    }

    //to start the setting screen once clicked
    public void onClickSCSetting(View view) {
        Intent intent = new Intent(this, SCSettingActivity.class);
        sc_setting = findViewById(R.id.SCSettingButton);
        startActivity(intent);
    }

    //to start the finish screen once clicked
    public void onClickSCDone(View view){
        Intent intent = new Intent(this, ShapeClickerEndActivity.class);
        sc_done = findViewById(R.id.sc_finish_button);
        startActivity(intent);
    }
}
