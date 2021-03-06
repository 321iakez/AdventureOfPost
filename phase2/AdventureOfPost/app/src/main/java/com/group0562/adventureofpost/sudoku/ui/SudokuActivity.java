package com.group0562.adventureofpost.sudoku.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.database.DatabaseHelper;
import com.group0562.adventureofpost.sudoku.SudokuPresenter;
import com.group0562.adventureofpost.sudoku.SudokuStats;
import com.group0562.adventureofpost.sudoku.SudokuView;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class SudokuActivity extends AppCompatActivity implements SudokuView, Observer,
        SudokuPauseDialog.PauseDialogListener, SudokuEndDialog.EndDialogListener {

    private SudokuPresenter presenter;
    private SudokuCellGridView gridView;
    private SudokuNumPadGridView numPadView;

    private TextView moveStats;
    private TextView conflictStats;
    private TextView timeStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        int gridSize = getIntent().getStringExtra("gridSize").equals("6x6") ? 6 : 9;
        String difficulty = getIntent().getStringExtra("difficulty");
        String username = getIntent().getStringExtra("username");
        boolean resume = getIntent().getBooleanExtra("resume", false);

        // Create presenter
        if (!resume) {
            presenter = new SudokuPresenter(this, new SudokuStats(username), gridSize, difficulty, "");
        } else {
            DatabaseHelper db = new DatabaseHelper(this);
            String gameState = db.retrieveGameState("sudoku", username);

            String game_stats = db.retrieveResumeStats();
            List<String> result = Arrays.asList(game_stats.split("\\s*,\\s*"));

            if (!gameState.equals("")) {
                presenter = new SudokuPresenter(this, new SudokuStats(result), gridSize, difficulty, gameState);
            } else {
                presenter = new SudokuPresenter(this, new SudokuStats(username), gridSize, difficulty, "");
                Toast.makeText(getApplicationContext(), "No saved games, new game created!", Toast.LENGTH_SHORT).show();
            }
        }

        presenter.addObserver(this);

        // Call helper methods to initialize components
        initSudokuGrid();
        initSudokuNumpad();
        initStatsView();

        addListenerRemoveButton();
        addListenerResetButton();
        addListenerExitButton();

        // Update the board to insert board data into newly generated grid
        updateBoard();
    }

    /**
     * Initializes Sudoku grid based on user selected size of the board.
     */
    private void initSudokuGrid() {
        gridView = findViewById(R.id.grid);
        gridView.setPresenter(presenter);
        gridView.createTileButtons(this);
        gridView.setNumColumns(presenter.getDim());

        // Observer sets up desired dimensions as well as calls our displayGrid function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int width = gridView.getMeasuredWidth();
                        int height = gridView.getMeasuredHeight();
                        int colWidth = width / presenter.getDim();
                        int colHeight = height / presenter.getDim();

                        GridSizeAdaptor adaptor = new GridSizeAdaptor(gridView.getTileButtons(), colWidth, colHeight, false);
                        gridView.setAdapter(adaptor);
                    }
                });
    }

    /**
     * Initializes Sudoku number pad based on user selected size of the board.
     */
    private void initSudokuNumpad() {
        numPadView = findViewById(R.id.numPad);
        numPadView.setPresenter(presenter);
        numPadView.createTileButtons(this);
        numPadView.setNumColumns(presenter.getDim());

        // Observer sets up desired dimensions as well as calls our displayGrid function
        numPadView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        numPadView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int width = numPadView.getMeasuredWidth();
                        int height = numPadView.getMeasuredHeight();
                        int colWidth = width / presenter.getDim();

                        GridSizeAdaptor adaptor = new GridSizeAdaptor(numPadView.getTileButtons(), colWidth, height, true);
                        numPadView.setAdapter(adaptor);
                    }
                });
    }

    private void initStatsView() {
        moveStats = findViewById(R.id.statsMoveNum);
        moveStats.setText("0");

        conflictStats = findViewById(R.id.statsConflictNum);
        conflictStats.setText("0");

        timeStats = findViewById(R.id.statsTime);
        timeStats.setText("0");
    }

    private void addListenerRemoveButton() {
        findViewById(R.id.removeButton).setOnClickListener(v -> presenter.removeNum());
    }

    private void addListenerResetButton() {
        findViewById(R.id.resetButton).setOnClickListener(v -> presenter.resetGameBoard());
    }

    private void addListenerExitButton() {
        findViewById(R.id.exit_button).setOnClickListener(v -> {
            SudokuPauseDialog pauseDialog = new SudokuPauseDialog();
            pauseDialog.show(getSupportFragmentManager(), "pause dialog");
        });
    }

    private void updateBoard() {
        for (int row = 0; row < presenter.getDim(); row++) {
            for (int col = 0; col < presenter.getDim(); col++) {
                String loadValue = "";
                if (presenter.getCellValue(row, col) != 0) {
                    loadValue = String.valueOf(presenter.getCellValue(row, col));
                }

                gridView.loadValues(row, col, loadValue);
            }
        }
    }

    @Override
    public void saveGame(SudokuPauseDialog.Modes mode) {
        if (mode.equals(SudokuPauseDialog.Modes.EXIT_NO_SAVE)) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);
        } else if (mode.equals(SudokuPauseDialog.Modes.EXIT_SAVE)) {
            presenter.saveResumeStats(this);
            presenter.saveBoard(this, getIntent().getStringExtra("username"));

            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);

        }
    }

    @Override
    public void endGame(SudokuEndDialog.Modes mode) {
        if (mode.equals(SudokuEndDialog.Modes.EXIT_STAT)) {
            presenter.saveStats(this);
        }

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
    }

    @Override
    public void onGameComplete() {
        SudokuEndDialog dialog = new SudokuEndDialog();
        dialog.show(getSupportFragmentManager(), "end dialog");
    }

    @Override
    public InputStream getPresetBoardFile(String difficulty, int gridSize) {

        int file;
        switch (difficulty) {
            case "Easy":
            default:
                file = (gridSize == 9) ? R.raw.sudoku9_easy : R.raw.sudoku6_easy;
                break;
            case "Medium":
                file = (gridSize == 9) ? R.raw.sudoku9_medium : R.raw.sudoku6_medium;
                break;
            case "Hard":
                file = (gridSize == 9) ? R.raw.sudoku9_hard : R.raw.sudoku6_hard;
                break;
        }
        return getResources().openRawResource(file);
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        // Update stats display
        moveStats.setText(String.valueOf(presenter.getMoves()));
        conflictStats.setText(String.valueOf(presenter.getConflicts()));
        timeStats.setText(String.valueOf(presenter.getTime()));

        // Update board display
        updateBoard();
    }
}
