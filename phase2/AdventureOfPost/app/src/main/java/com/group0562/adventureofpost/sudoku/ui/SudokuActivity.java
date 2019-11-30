package com.group0562.adventureofpost.sudoku.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.sudoku.SudokuPresenter;
import com.group0562.adventureofpost.sudoku.SudokuView;

import java.io.InputStream;
import java.util.List;

public class SudokuActivity extends AppCompatActivity implements SudokuView, PauseDialog.PauseDialogListener {

    private SudokuPresenter presenter;
    private SudokuCellGridView gridView;
    private SudokuNumPadGridView numPadView;

    private final String RETURN_NO_SAVE = "RETURN_NO_SAVE";
    private final String RETURN_SAVE = "RETURN_SAVE";
    private final String RESUME = "RESUME";

    private TextView moveStats;
    private TextView conflictStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        int gridSize = getIntent().getStringExtra("gridSize").equals("6x6") ? 6 : 9;
        String difficulty = getIntent().getStringExtra("difficulty");

        presenter = new SudokuPresenter(this, gridSize, difficulty);

        // Call helper methods to initialize components
        initSudokuGrid();
        initSudokuNumpad();
        initStatsView();

        addListenerRemoveButton();
        addListenerResetButton();
        addListenerExitButton();

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

    /**
     * Initializes Sudoku grid based on user selected size of the board.
     */
    private void initSudokuGrid() {
        gridView = findViewById(R.id.grid);
        gridView.setPresenter(presenter);
        gridView.createTileButtons( this);
        gridView.setNumColumns(presenter.getDim());

        // Observer sets up desired dimensions as well as calls our displayGrid function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        int colWidth = displayWidth / presenter.getDim();
                        int colHeight = displayHeight / presenter.getDim();

                        gridView.setAdapter(new GridSizeAdaptor(gridView.getTileButtons(), colWidth, colHeight));
                    }
                });
    }

    /**
     * Initializes Sudoku number pad based on user selected size of the board.
     */
    private void initSudokuNumpad() {
        numPadView = findViewById(R.id.numPad);
        numPadView.setPresenter(presenter);
        numPadView.createTileButtons(this, gridView);
        numPadView.setNumColumns(presenter.getDim());

        // Observer sets up desired dimensions as well as calls our displayGrid function
        numPadView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        numPadView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int displayWidth = numPadView.getMeasuredWidth();
                        int displayHeight = numPadView.getMeasuredHeight();

                        int colWidth = displayWidth / presenter.getDim();

                        numPadView.setAdapter(new GridSizeAdaptor(numPadView.getTileButtons(), colWidth, displayHeight));
                    }
                });
    }

    private void initStatsView() {
        moveStats = findViewById(R.id.statsMoveNum);
        moveStats.setText("0");

        conflictStats = findViewById(R.id.statsConflictNum);
        conflictStats.setText("0");
    }

    private void addListenerRemoveButton() {
        findViewById(R.id.removeButton).setOnClickListener(v -> {
            presenter.removeNum();
            gridView.removeValue(presenter.getCurrRow(), presenter.getCurrCol());

            // Update
            presenter.update();
        });
    }

    private void addListenerResetButton() {
        findViewById(R.id.resetButton).setOnClickListener(v -> {
            List<int[]> resetCells = presenter.resetGameBoard();
            for (int[] cellLoc : resetCells) {
                gridView.removeValue(cellLoc[0], cellLoc[1]);
            }

            // Update
            presenter.update();
        });
    }

    private void addListenerExitButton() {
        findViewById(R.id.exit_button).setOnClickListener(v -> {
            PauseDialog pauseDialog = new PauseDialog();
            pauseDialog.show(getSupportFragmentManager(), "pause dialog");
        });
    }

    @Override
    public void saveGame(String mode) {
        if (mode.equals(RETURN_NO_SAVE)) {
            System.out.println("returned without save");
        } else if (mode.equals(RETURN_SAVE)) {
            System.out.println("returned with save");
        } else {
            System.out.println("Resumed");
        }
    }

    @Override
    public void updateStats() {
        moveStats.setText(String.valueOf(presenter.getMoves()));
        conflictStats.setText(String.valueOf(presenter.getConflicts()));
    }

    @Override
    public void onGameComplete() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getApplicationContext());
        dialogBuilder.setTitle("Puzzle Completed!")
                .setMessage("Congratulation! You have completed the puzzle.")
                .setPositiveButton("Restart", (dialog, which) -> {
                    Intent intent = new Intent(this, SudokuStartActivity.class);
                    this.startActivity(intent);
                })
                .setNeutralButton("Home", (dialog, which) -> {
                    Intent intent = new Intent(this, GameActivity.class);
                    this.startActivity(intent);
                });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
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
