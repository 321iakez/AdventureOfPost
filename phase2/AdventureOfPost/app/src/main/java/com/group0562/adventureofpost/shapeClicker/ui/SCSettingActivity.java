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

public class SCSettingActivity extends AppCompatActivity {
    private Button sc_back_to_game;

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
    }


    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.sc_preferences, rootKey);
        }
    }

    /*to switch the settings and go back to the game page*/
    public void onClickBackToGame(View view) {
        sc_back_to_game = findViewById(R.id.sc_back_to_game);
        SharedPreferences sc_color_list = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sc_shape_list = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sc_difficulty_list = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sc_mode_list = PreferenceManager.getDefaultSharedPreferences(this);
        String color_choice = sc_color_list.getString(getString(R.string.sc_color_key), "Black");
        String shape_choice = sc_shape_list.getString(getString(R.string.sc_shape_key), "Circle");
        String difficulty_choice = sc_difficulty_list.getString(getString(R.string.sc_difficulty_key), "Easy");
        String mode_choice = sc_mode_list.getString(getString(R.string.sc_mode_key), "Normal");
        SCSetting.setColor(color_choice);
        SCSetting.setDifficulty(difficulty_choice);
        SCSetting.setShape(shape_choice);
        SCSetting.setMode(mode_choice);
//        SCNormalMode.reset();
        Intent intent = new Intent(this, ShapeClickerActivity.class);
        startActivity(intent);
    }
}