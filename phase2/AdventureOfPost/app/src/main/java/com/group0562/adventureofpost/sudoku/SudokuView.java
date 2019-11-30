package com.group0562.adventureofpost.sudoku;

import java.io.InputStream;

public interface SudokuView {

    void onGameComplete();

    InputStream getPresetBoardFile(String difficulty, int gridSize);

    void updateStats();
}
