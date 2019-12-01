package com.group0562.adventureofpost.sudoku;

import android.content.Context;

import java.io.InputStream;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;

/**
 * Sudoku presenter class that bridges the UI and game logic.
 */
public class SudokuPresenter {

    /**
     * Virtual board instance.
     */
    private Board gameBoard;

    /**
     * Sudoku UI view instance.
     */
    private SudokuView view;

    /**
     * Sudoku stats tracker instance.
     */
    private SudokuStats gameStats;

    private SudokuStats.CountUpTimer timer;

    /**
     * Position of currently selected cell.
     */
    private int currRow = -1;
    private int currCol = -1;

    public SudokuPresenter(SudokuView view, SudokuStats gameStats, int gridSize, String difficulty) {
        this.gameStats = gameStats;
        this.view = view;

        int[][] parsedBoard = getRandomPuzzle(view.getPresetBoardFile(difficulty, gridSize), gridSize);
        this.gameBoard = new Board(parsedBoard, gridSize, gridSize);
        System.out.println(getGameState());

        timer = new SudokuStats.CountUpTimer(3600000) {
            @Override
            void onTick(int second) {
                gameStats.updateTime(second);
            }
        };
        timer.start();
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

    public void addObserver(Observer observer) {
        gameStats.addObserver(observer);
        gameBoard.addObserver(observer);
    }

    /**
     * This method returns the state of the board/game as a String, so that the state of the game
     * can be stored in the database.
     */
    private String getGameState() {
        return gameBoard.getBoardData() + ',' + gameStats.getMoves() + ',' + gameStats.getConflicts();
    }

    /* Getter and setters for presenter class. */
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

    /* Methods for UI to access the Board class without jumping architectural layers. */
    public void removeNum() {
        gameBoard.insertNum(currRow, currCol, 0);
    }

    public boolean addNum(int input) {
        boolean insertSuccess = gameBoard.insertNum(currRow, currCol, input);
        if (insertSuccess && gameBoard.checkFull()) {
            timer.cancel();
            view.onGameComplete();
        }
        return insertSuccess;
    }

    public int getCellValue(int row, int col) {
        return gameBoard.getCell(row, col).getValue();
    }

    public boolean getCellLocked(int row, int col) {
        return gameBoard.getCell(row, col).isLocked();
    }

    public void resetGameBoard() {
        gameStats.reset();
        gameBoard.resetBoard();

        timer.cancel();
        timer.start();
    }

    public int getDim() {
        return gameBoard.getDim();
    }

    /* Methods for UI to access the SudokuStats class without jumping architectural layers. */
    public int getMoves() {
        return gameStats.getMoves();
    }

    public int getConflicts() {
        return gameStats.getConflicts();
    }

    public long getTime() {
        return gameStats.getGameTime();
    }

    public void addMoves() {
        gameStats.addMoves();
    }

    public void addConflicts() {
        gameStats.addConflicts();
    }

    public void saveStats(Context context) {
        gameStats.saveStats(context);
    }
}
