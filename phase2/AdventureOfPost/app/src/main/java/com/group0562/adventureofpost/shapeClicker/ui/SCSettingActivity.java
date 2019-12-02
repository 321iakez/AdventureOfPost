package com.group0562.adventureofpost.shapeClicker.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.ScoreboardActivity;
import com.group0562.adventureofpost.shapeClicker.SCSetting;

/**this class serves as the setting page for SC shape clicker */
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

    /* to switch the settings and go back to the game page */
    public void onClickStartSC(View view) {
        SharedPreferences manager = PreferenceManager.getDefaultSharedPreferences(this);

        SCSetting.setColor(manager.getString(getString(R.string.sc_color_key), "Black"));
        SCSetting.setDifficulty(manager.getString(getString(R.string.sc_difficulty_key), "Easy"));
        SCSetting.setShape(manager.getString(getString(R.string.sc_shape_key), "Circle"));
        SCSetting.setMode(manager.getString(getString(R.string.sc_mode_key), "Normal"));

        Intent intent = new Intent(this, ShapeClickerActivity.class);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        SCSetting.setUsername(getIntent().getStringExtra("username"));
        startActivity(intent);
    }

    /* this is for the scoreboard for SC */
    private void addListenerScoreboard() {
        findViewById(R.id.playerScoreButton).setOnClickListener(v -> {
            Intent intent = new Intent(this, ScoreboardActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            intent.putExtra("currGame", "shapeClicker");
            startActivity(intent);
        });
    }
}