package com.group0562.adventureofpost.trivia.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.trivia.Trivia;

public class TriviaStartActivity extends AppCompatActivity {
    Trivia game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_start);
        game = (Trivia)getIntent().getSerializableExtra("game");
    }

    public void onClickEasy(View view) {
        game.setDiff("easy");
        startIntent();
    }

    public void onClickMedium(View view) {
        game.setDiff("medium");
        startIntent();
    }

    public void onClickHard(View view) {
        game.setDiff("hard");
        startIntent();
    }

    private void startIntent () {
        Intent intent = new Intent(this, TriviaActivity.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }
}
