package com.group0562.adventureofpost.shapeClicker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.group0562.adventureofpost.R;

public class ShapeClickerActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.group0562.AdventureOfPost.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_clicker);
    }

    public void onClickShapeClickerSetting(View view) {
        Intent intent = new Intent(this, SCSettingsActivity.class);
        TextView textView = (TextView) findViewById(R.id.SCSettingButton);
        String message = textView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
