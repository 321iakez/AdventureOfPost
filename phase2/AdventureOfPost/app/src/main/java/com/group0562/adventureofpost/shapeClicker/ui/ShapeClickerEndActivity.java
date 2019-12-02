package com.group0562.adventureofpost.shapeClicker.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.database.DatabaseHelper;

public class ShapeClickerEndActivity extends AppCompatActivity {
    private String username;

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

    /*to go back to the page where you can select games*/
    public void onClickSaveReturnHome(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        username = getIntent().getStringExtra("username");
        saveStats(this, getIntent().getStringExtra("data"));
        startActivity(intent);
    }

    /** this lets you save the stats if the player intended to */
    private void saveStats(Context context, String data) {
        DatabaseHelper db = new DatabaseHelper(context);
        String[] data_list = data.split(",");
        long newRowId = db.insertShapeClickerStats(this.username, (long) Double.parseDouble(data_list[0]), Integer.parseInt(data_list[1]), Integer.parseInt(data_list[2]));
        Log.i("ShapeClicker", "Stats inserted at row" + newRowId);
    }

}
