package com.group0562.adventureofpost.shapeClicker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group0562.adventureofpost.R;

public class ShapeClickerActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.group0562.AdventureOfPost.MESSAGE";
    Button sc_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_clicker);
    }

    public void onClickSCSetting(View view) {
        Intent intent = new Intent(this, SCSettingActivity.class);
        sc_setting = findViewById(R.id.SCSettingButton);
        startActivity(intent);
    }
}
