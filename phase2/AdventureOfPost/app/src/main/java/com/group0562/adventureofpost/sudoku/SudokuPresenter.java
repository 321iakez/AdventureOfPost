package com.group0562.adventureofpost.sudoku;

import com.group0562.adventureofpost.Puzzles;

import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SudokuPresenter extends Puzzles {

    private Board gameBoard;
    private SudokuView view;

    private int currRow = 0;
    private int currCol = 0;

    public SudokuPresenter(InputStream file, SudokuView view) {
        super(new SudokuStats(100000));
        this.view = view;
        this.gameBoard = new Board(getRandomPuzzle(file), 6, 6);
    }

    /**
     * Randomly selects a puzzle from the puzzles
     *
     * @return a 2-D integer array puzzle.
     */
    private int[][] getRandomPuzzle(InputStream file) {
        // Read file
        Random rand = new Random();
        Scanner scanner = new Scanner(file);

        // Randomly select a line from the puzzles
        int n = 0;
        String[] result = new String[36];
        while (scanner.hasNext()) {
            n++;
            String line = scanner.nextLine();
            if (rand.nextInt(n) == 0)
                result = line.split(",");
        }

        // Convert string to 2D int array
        int[][] puzzle = new int[6][6];
        int index = 0;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                puzzle[row][col] = Integer.parseInt(result[index]);
                index++;
            }
        }

        return puzzle;
    }

    /**
     * Since every user input must follow not have any conflicts with the existing board, the game
     * if complete iff the board is full.
     */
    @Override
    public void checkComplete() {
        if (gameBoard.checkFull()) {
            setPuzzleComplete(true);
        }
    }

    @Override
    public void update() {
        // Check complete
        checkComplete();
        if (getPuzzleComplete()) {
            onStop();
        }
    }

    @Override
    public void onStop() {
        view.onGameComplete();
    }

    public int getCurrCol() {
        return currCol;
    }

    public int getCurrRow() {
        return currRow;
    }

    public void setCurrCol(int currCol) {
        this.currCol = currCol;
    }

    public void setCurrRow(int currRow) {
        this.currRow = currRow;
    }

    public void removeNum() {
        gameBoard.insertNum(currRow, currCol, 0);
    }

    public boolean addNum(int input) {
        return gameBoard.insertNum(currRow, currCol, input);
    }

    public int getCellValue() {
        return gameBoard.getCell(currRow, currCol).getValue();
    }

    public boolean getCellLocked() {
        return gameBoard.getCell(currRow, currCol).isLocked();
    }

    public List<int[]> resetGameBoard() {
        return gameBoard.resetBoard();
    }

    public int getMoves() {
        return gameBoard.getMoves();
    }

    public int getConflicts() {
        return gameBoard.getConflicts();
    }

    public void addMoves() {
        gameBoard.addMoves();
    }

    public void addConflicts() {
        gameBoard.addConflicts();
    }
}
