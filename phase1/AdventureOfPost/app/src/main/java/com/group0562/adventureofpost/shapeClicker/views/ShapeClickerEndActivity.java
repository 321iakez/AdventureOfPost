package com.group0562.adventureofpost.shapeClicker.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.MainActivity;
import com.group0562.adventureofpost.R;

public class ShapeClickerEndActivity extends AppCompatActivity {
    Button return_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_clicker_end);
    }

    public void onClickReturnHome(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        return_menu = findViewById(R.id.return_home);
        startActivity(intent);
    }
}
