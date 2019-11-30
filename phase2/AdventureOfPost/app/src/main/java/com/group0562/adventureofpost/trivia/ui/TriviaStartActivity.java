package com.group0562.adventureofpost.trivia.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.trivia.Trivia;

public class TriviaStartActivity extends AppCompatActivity {

    private final String[] OP_OPTIONS = new String[]{"Addition", "Subtraction", "Multiplication"};
    private final String[] DIFFICULTY_OPTIONS = new String[]{"Easy", "Medium", "Hard"};

    private Spinner operations;
    private Spinner difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_start);
        operations = findViewById(R.id.op);
        ArrayAdapter<String> gridOptionAdaptor = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, OP_OPTIONS);
        operations.setAdapter(gridOptionAdaptor);

        difficulty = findViewById(R.id.difficultySelect);
        ArrayAdapter<String> difficultyOptionAdaptor = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, DIFFICULTY_OPTIONS);
        difficulty.setAdapter(difficultyOptionAdaptor);

        findViewById(R.id.startButton).setOnClickListener(this::onClickStart);

    }

    void onClickStart(View view) {
        Intent intent = new Intent(this, TriviaActivity.class);
        intent.putExtra("op", operations.getSelectedItem().toString());
        intent.putExtra("diff", difficulty.getSelectedItem().toString());
        startActivity(intent);
    }


}
