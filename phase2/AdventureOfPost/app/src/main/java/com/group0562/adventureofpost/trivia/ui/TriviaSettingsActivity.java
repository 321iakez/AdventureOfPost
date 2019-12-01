package com.group0562.adventureofpost.trivia.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.trivia.Trivia;

public class TriviaSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trivia_settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void onClickSave(View view){
        Intent intent = new Intent(this, TriviaActivity.class);
        SharedPreferences trivia_BackgroundColor_list = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences trivia_ButtonColor_list = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences trivia_TextColor_list = PreferenceManager.getDefaultSharedPreferences(this);

        String backgroundColor = trivia_BackgroundColor_list.getString(getString(R.string.Trivia_BackgroundColor_key), "Black");
        String buttonColor = trivia_ButtonColor_list.getString(getString(R.string.Trivia_ButtonColor_key), "Black");
        String textColor = trivia_TextColor_list.getString(getString(R.string.Trivia_TextColor_key), "Black");

        intent.putExtra("saveState", getIntent().getStringExtra("saveState"));
        intent.putExtra("backgroundColor", backgroundColor);
        intent.putExtra("buttonColor", buttonColor);
        intent.putExtra("textColor", textColor);
        intent.putExtra("username", getIntent().getStringExtra("username"));

        startActivity(intent);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.trivia_preferences, rootKey);
        }
    }
}