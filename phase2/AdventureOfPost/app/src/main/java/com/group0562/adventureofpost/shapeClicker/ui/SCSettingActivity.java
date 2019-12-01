package com.group0562.adventureofpost.shapeClicker.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.shapeClicker.SCSetting;
import com.group0562.adventureofpost.sudoku.ui.SudokuScoreboardActivity;

public class SCSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sc_settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        addListenerScoreboard();
    }


    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.sc_preferences, rootKey);
        }
    }

    /*to switch the settings and go back to the game page*/
    public void onClickBackToGame(View view) {
        SharedPreferences manager = PreferenceManager.getDefaultSharedPreferences(this);

        String color_choice = manager.getString(getString(R.string.sc_color_key), "Black");
        String shape_choice = manager.getString(getString(R.string.sc_shape_key), "Circle");
        String difficulty_choice = manager.getString(getString(R.string.sc_difficulty_key), "Easy");
        String mode_choice = manager.getString(getString(R.string.sc_mode_key), "Normal");

        SCSetting.setColor(color_choice);
        SCSetting.setDifficulty(difficulty_choice);
        SCSetting.setShape(shape_choice);
        SCSetting.setMode(mode_choice);

        Intent intent = new Intent(this, ShapeClickerActivity.class);
        intent.putExtra("username", intent.getStringExtra("username"));
        startActivity(intent);
    }

    private void addListenerScoreboard() {
        findViewById(R.id.playerScoreButton).setOnClickListener(v -> {
            Intent intent = new Intent(this, SudokuScoreboardActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            intent.putExtra("currGame", "shapeClicker");
            startActivity(intent);
        });
    }
}