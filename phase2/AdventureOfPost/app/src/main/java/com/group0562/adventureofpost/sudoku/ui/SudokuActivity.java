package com.group0562.adventureofpost.sudoku.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.sudoku.SudokuPresenter;
import com.group0562.adventureofpost.sudoku.SudokuView;
import com.group0562.adventureofpost.sudoku.PauseDialog;
import java.io.InputStream;
import java.util.List;

public class SudokuActivity extends AppCompatActivity implements SudokuView, SudokuStatsFragment.OnFragmentInteractionListener, PauseDialog.PauseDialogListener{

    private SudokuPresenter presenter;
    private SudokuCellGridView gridView;

    private static int columnWidth, columnHeight;

    private SudokuStatsFragment statsFrag;
    private final String RETURN_NO_SAVE = "RETURN_NO_SAVE";
    private final String RETURN_SAVE = "RETURN_SAVE";
    private final String RESUME = "RESUME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        int gridSize = getIntent().getStringExtra("gridSize").equals("6x6") ? 6 : 9;
        String difficulty = getIntent().getStringExtra("difficulty");

        presenter = new SudokuPresenter(this, gridSize, difficulty);

        gridView = findViewById(R.id.grid);
        gridView.createTileButtons(presenter, this, presenter.getDim());
        gridView.setNumColumns(presenter.getDim());
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / presenter.getDim();
                        columnHeight = displayHeight / presenter.getDim();

                        display();
                    }
                });

        findViewById(R.id.removeButton).setOnClickListener(this::onClickRemove);
        findViewById(R.id.resetButton).setOnClickListener(this::onClickReset);
        Button exitButton = (Button) findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        statsFrag = (SudokuStatsFragment) getSupportFragmentManager().findFragmentById(R.id.statsFragment);

        // Display initial board values
        for (int row = 0; row < presenter.getDim(); row++) {
            for (int col = 0; col < presenter.getDim(); col++) {
                int cellValue = presenter.getCellValue(row, col);
                if (cellValue != 0) {
                    gridView.loadValues(row, col, cellValue);
                }
            }
        }
    }

    @Override
    public void saveGame(String mode) {
        if(mode.equals(RETURN_NO_SAVE)){
            System.out.println("returned without save");
        } else if (mode.equals(RETURN_SAVE)){
            System.out.println("returned with save");
        } else {
            System.out.println("Resumed");
        }
    }

    public void openDialog(){
        PauseDialog pauseDialog = new PauseDialog();
        pauseDialog.show(getSupportFragmentManager(), "pause dialog");
    }
    public void display() {
        gridView.setAdapter(new GridSizeAdaptor(gridView.getTileButtons(), columnWidth, columnHeight));
    }

    public void onClickNumpad(View view) {
        if (presenter.getCurrCol() == -1 || presenter.getCurrRow() == -1) {
            Toast.makeText(getApplicationContext(), "No cell selected!", Toast.LENGTH_SHORT).show();
            return;
        }

        Button numClicked = (Button) view;
        int newValue = Integer.parseInt(numClicked.getTag().toString());

        // Update backend board
        boolean updateSuccess = presenter.addNum(newValue);

        // Load value on board
        if (updateSuccess) {
            gridView.loadValues(presenter.getCurrRow(), presenter.getCurrCol(), newValue);
            updateStats(true, false);
        } else {
            Toast.makeText(getApplicationContext(), "Conflict detected!", Toast.LENGTH_SHORT).show();
            updateStats(false, true);
        }

        // Update
        presenter.update();
    }

    void onClickRemove(View view) {
        presenter.removeNum();
        gridView.removeValue(presenter.getCurrRow(), presenter.getCurrCol());

        // Update
        presenter.update();
    }

    void onClickReset(View view) {
        List<int[]> resetCells = presenter.resetGameBoard();
        for (int[] cellLoc : resetCells) {
            gridView.removeValue(cellLoc[0], cellLoc[1]);
        }

        // Update
        updateStats(false, false);
        presenter.update();
    }

    public void updateStats(boolean newMove, boolean newConflict) {
        if (newMove) {
            presenter.addMoves();
        }

        if (newConflict) {
            presenter.addConflicts();
        }

        int moves = presenter.getMoves();
        int conflicts = presenter.getConflicts();
        statsFrag.updateStats(moves, conflicts);
    }

    private void endDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getApplicationContext());
        dialogBuilder.setTitle("Puzzle Completed!")
                .setMessage("Congratulation! You have completed the puzzle.")
                .setPositiveButton("Restart", (dialog, which) -> this.recreate())
                .setNeutralButton("Home", (dialog, which) -> {
                    Intent intent = new Intent(this, GameActivity.class);
                    this.startActivity(intent);
                });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    @Override
    public void onFragmentInteraction(View view) {

    }

    @Override
    public void onGameComplete() {
        endDialog();
    }

    @Override
    public InputStream getPresetBoardFile(String difficulty, int gridSize) {

        InputStream result = null;
        if (gridSize == 9) {
            switch (difficulty) {
                case "Easy":
                    result = getResources().openRawResource(R.raw.sudoku9_easy);
                    break;
                case "Medium":
                    result = getResources().openRawResource(R.raw.sudoku9_medium);
                    break;
                case "Hard":
                    result = getResources().openRawResource(R.raw.sudoku9_hard);
                    break;
            }
        } else {
            switch (difficulty) {
                case "Easy":
                    result = getResources().openRawResource(R.raw.sudoku6_easy);
                    break;
                case "Medium":
                    result = getResources().openRawResource(R.raw.sudoku6_medium);
                    break;
                case "Hard":
                    result = getResources().openRawResource(R.raw.sudoku6_hard);
                    break;
            }
        }
        return result;
    }
}
