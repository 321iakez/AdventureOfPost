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

    public SudokuPresenter(SudokuView view, int gridSize, String difficulty) {
        super(new SudokuStats(100000));
        this.view = view;

        int[][] parsedBoard = getRandomPuzzle(view.getPresetBoardFile(difficulty, gridSize), gridSize);
        this.gameBoard = new Board(parsedBoard, gridSize, gridSize);
    }

    /**
     * Randomly selects a puzzle from the puzzles
     *
     * @return a 2-D integer array puzzle.
     */
    private int[][] getRandomPuzzle(InputStream file, int gridSize) {
        // Read file
        Random rand = new Random();
        Scanner scanner = new Scanner(file);

        // Randomly select a line from the puzzles
        String result = scanner.nextLine();
        int lineNum = rand.nextInt(10);
        for (int i = 0; i < lineNum; i++) {
            result = scanner.nextLine();
        }

        // Convert string to 2D int array
        int[][] puzzle = new int[gridSize][gridSize];
        int index = 1;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                puzzle[row][col] = Integer.parseInt(result.split("")[index]);
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

    public int getCellValue(int row, int col) {
        return gameBoard.getCell(row, col).getValue();
    }

    public boolean getCellLocked(int row, int col) {
        return gameBoard.getCell(row, col).isLocked();
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

    public int getDim() {
        return gameBoard.getDim();
    }
}
