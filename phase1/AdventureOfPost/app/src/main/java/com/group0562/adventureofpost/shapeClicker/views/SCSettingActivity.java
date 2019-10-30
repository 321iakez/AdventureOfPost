package com.group0562.adventureofpost.shapeClicker.views;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.shapeClicker.Shape;
import com.group0562.adventureofpost.shapeClicker.ShapeClicker;
import com.group0562.adventureofpost.shapeClicker.ShapeClickerGameView;

import java.util.List;

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
        SharedPreferences sc_color_list = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sc_shape_list = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sc_difficulty_list = PreferenceManager.getDefaultSharedPreferences(this);
        String color_choice = sc_color_list.getString(getString(R.string.sc_color_key), "FF000000");
        String shape_choice = sc_shape_list.getString(getString(R.string.sc_shape_key), "Circle");
        String difficulty_choice = sc_difficulty_list.getString(getString(R.string.sc_difficulty_key), "Easy");
        if (color_choice.equals("true")) {ShapeClickerGameView.setColor("FF000000");}
        else {ShapeClickerGameView.setColor(color_choice); }
        if (difficulty_choice.equals("Hard")){Shape.setRadius(15.0);}
        if (!shape_choice.equals("true")){ShapeClicker.setShape(difficulty_choice);}
    }



    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.sc_preferences, rootKey);
        }
    }
}