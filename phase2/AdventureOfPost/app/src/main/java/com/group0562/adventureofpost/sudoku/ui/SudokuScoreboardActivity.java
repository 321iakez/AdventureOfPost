package com.group0562.adventureofpost.sudoku.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.database.DatabaseHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.group0562.adventureofpost.shapeClicker.ShapeClickerStats.SC_STAT1;
import static com.group0562.adventureofpost.shapeClicker.ShapeClickerStats.SC_STAT2;
import static com.group0562.adventureofpost.shapeClicker.ShapeClickerStats.SC_STAT3;
import static com.group0562.adventureofpost.sudoku.SudokuStats.SUDOKU_STAT1;
import static com.group0562.adventureofpost.sudoku.SudokuStats.SUDOKU_STAT2;
import static com.group0562.adventureofpost.sudoku.SudokuStats.SUDOKU_STAT3;
import static com.group0562.adventureofpost.trivia.TriviaStats.TRIVIA_STAT1;
import static com.group0562.adventureofpost.trivia.TriviaStats.TRIVIA_STAT2;
import static com.group0562.adventureofpost.trivia.TriviaStats.TRIVIA_STAT3;

public class SudokuScoreboardActivity extends AppCompatActivity {

    private LinearLayout stat1Layout;
    private LinearLayout stat2Layout;
    private LinearLayout stat3Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_scoreboard);

        stat1Layout = findViewById(R.id.stat1_layout);
        stat2Layout = findViewById(R.id.stat2_layout);
        stat3Layout = findViewById(R.id.stat3_layout);

        switch (getIntent().getStringExtra("currGame")) {
            case ("sudoku"):
                displayPlayerScore(SUDOKU_STAT1, SUDOKU_STAT2, SUDOKU_STAT3, "Time (s)", "Moves", "Conflicts");
                break;
            case ("trivia"):
                displayPlayerScore(TRIVIA_STAT1, TRIVIA_STAT2, TRIVIA_STAT3, "Correct", "Incorrect", "Score");
                break;
            case ("shapeClicker"):
                displayPlayerScore(SC_STAT1, SC_STAT2, SC_STAT3, "Time (s)", "Score", "Lives");
                break;
        }
    }

    Map<String, int[]> getTopPlayerStats(Context context, String stat1, String stat2, String stat3) {
        DatabaseHelper db = new DatabaseHelper(context);
        Map<String, List<Integer>> data = db.playerStats(getIntent().getStringExtra("username"), getIntent().getStringExtra("currGame"));

        int[] stat1Top = new int[3];
        int[] stat2Top = new int[3];
        int[] stat3Top = new int[3];
        int index = 0;
        for (Integer item : data.get(stat1)) {
            if (index >= 3) {
                break;
            }

            stat1Top[index] = item;
            index++;
        }

        index = 0;
        for (Integer item : data.get(stat2)) {
            if (index >= 3) {
                break;
            }

            stat2Top[index] = item;
            index++;
        }

        index = 0;
        for (Integer item : data.get(stat3)) {
            if (index >= 3) {
                break;
            }

            stat3Top[index] = item;
            index++;
        }

        Map<String, int[]> result = new HashMap<>();
        result.put(stat1, stat1Top);
        result.put(stat2, stat2Top);
        result.put(stat3, stat3Top);

        return result;
    }

    private void displayPlayerScore(String stat1, String stat2, String stat3, String stat1Header,
                                    String stat2Header, String stat3Header) {
        Map<String, int[]> data = getTopPlayerStats(this, stat1, stat2, stat3);

        ((TextView) findViewById(R.id.stat1Header)).setText(stat1Header);
        System.out.println(data.toString());
        int[] stat1Top = data.get(stat1);
        for (int x = 0; x < stat1Top.length; x++) {
            scoreColumnView(String.valueOf(x + 1),
                    getIntent().getStringExtra("username"),
                    stat1Top[x] == 0 ? "-" : String.valueOf(stat1Top[x]),
                    stat1Layout);
        }

        ((TextView) findViewById(R.id.stat2Header)).setText(stat2Header);
        int[] stat2Top = data.get(stat2);
        for (int x = 0; x < stat2Top.length; x++) {
            scoreColumnView(String.valueOf(x + 1),
                    getIntent().getStringExtra("username"),
                    stat2Top[x] == 0 ? "-" : String.valueOf(stat2Top[x]),
                    stat2Layout);
        }

        ((TextView) findViewById(R.id.stat3Header)).setText(stat3Header);
        int[] stat3Top = data.get(stat3);
        for (int x = 0; x < stat3Top.length; x++) {
            scoreColumnView(String.valueOf(x + 1),
                    getIntent().getStringExtra("username"),
                    stat3Top[x] == 0 ? "-" : String.valueOf(stat3Top[x]),
                    stat3Layout);
        }
    }

    /**
     * Set the view of scores for each column.
     *
     * @param rankNum   rank number
     * @param user      the username
     * @param topScores the scores
     */

    private void scoreColumnView(String rankNum, String user, String topScores, LinearLayout parent) {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        TextView rank = new TextView(this);
        TextView name = new TextView(this);
        TextView score = new TextView(this);

        generateTextView(rank, rankNum);
        generateTextView(name, user);
        generateTextView(score, topScores);

        layout.addView(rank);
        layout.addView(name);
        layout.addView(score);

        parent.addView(layout);
    }

    /**
     * Generate a textView with specific setting.
     *
     * @param view the textView
     * @param text the text to be set
     */
    private void generateTextView(TextView view, String text) {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );
        param.setMargins(0, 20, 0, 0);
        view.setText(text);
        view.setTextSize(21);
        view.setGravity(Gravity.CENTER);
        view.setLayoutParams(param);
    }
}
