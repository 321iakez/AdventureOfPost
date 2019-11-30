package com.group0562.adventureofpost.sudoku;

import com.group0562.adventureofpost.Puzzles;

import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SudokuPresenter extends Puzzles {

    private Board gameBoard;
    private SudokuView view;
    private SudokuStats gameStats;

    private int currRow = -1;
    private int currCol = -1;

    public SudokuPresenter(SudokuView view, SudokuStats gameStats, int gridSize, String difficulty) {
        super(gameStats);
        this.gameStats = gameStats;
        this.view = view;

        int[][] parsedBoard = getRandomPuzzle(view.getPresetBoardFile(difficulty, gridSize), gridSize);
        this.gameBoard = new Board(parsedBoard, gridSize, gridSize);
        System.out.println(getGameState());
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
     * This method returns the state of the board/game as a String, so that the state of the game
     * can be stored in the database.
     */
    private String getGameState() {
        return gameBoard.getBoardData() + ',' + gameStats.getMoves() + ',' + gameStats.getConflicts();
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
        view.updateStats();

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

    /* Methods for UI to access the board class without jumping architectural layers. */
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
        gameStats.reset();
        return gameBoard.resetBoard();
    }

    public int getMoves() {
        return gameStats.getMoves();
    }

    public int getConflicts() {
        return gameStats.getConflicts();
    }

    public void addMoves() {
        gameStats.addMoves();
    }

    public void addConflicts() {
        gameStats.addConflicts();
    }

    public int getDim() {
        return gameBoard.getDim();
    }
}
