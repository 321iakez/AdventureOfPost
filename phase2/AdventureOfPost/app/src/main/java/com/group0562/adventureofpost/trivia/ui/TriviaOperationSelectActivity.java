package com.group0562.adventureofpost.trivia.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.trivia.Trivia;

public class TriviaOperationSelectActivity extends AppCompatActivity {
    Trivia game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_operation_select);
        game = (Trivia)getIntent().getSerializableExtra("game");
    }

    //TODO this need to be hooked up to setOperation
    public void onClickAdd(View view) {
        game.setDiff(1);
        startIntent();
    }

    public void onClickSub(View view) {
        game.setDiff(2);
        startIntent();
    }

    public void onClickMult(View view) {
        game.setDiff(3);
        startIntent();
    }

    private void startIntent () {
        Intent intent = new Intent(this, TriviaActivity.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }
}
