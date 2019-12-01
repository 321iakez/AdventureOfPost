package com.group0562.adventureofpost.sudoku;

import java.io.InputStream;

public interface SudokuView {

    /**
     * Action for UI when the game completes.
     */
    void onGameComplete();

    /**
     * Reads from Sudoku puzzle data files given difficulty and grid size.
     *
     * @param difficulty the difficulty user selected.
     * @param gridSize the size of the grid.
     * @return the corresponding puzzle file given the settings.
     */
    InputStream getPresetBoardFile(String difficulty, int gridSize);
}
