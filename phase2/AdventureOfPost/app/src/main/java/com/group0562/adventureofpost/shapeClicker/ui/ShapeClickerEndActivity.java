package com.group0562.adventureofpost.shapeClicker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.R;

public class ShapeClickerEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_clicker_end);
    }

    /*to go back to the page where you can select games*/
    public void onClickReturnHome(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }

}
