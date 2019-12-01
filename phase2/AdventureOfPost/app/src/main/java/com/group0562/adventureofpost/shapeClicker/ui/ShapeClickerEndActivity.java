package com.group0562.adventureofpost.shapeClicker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.R;

public class ShapeClickerEndActivity extends AppCompatActivity {
    Button return_menu;
    Button scoreboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_clicker_end);
    }

    /*to go back to the page where you can select games*/
    public void onClickReturnHome(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        return_menu = findViewById(R.id.return_home);
        startActivity(intent);
    }


}
