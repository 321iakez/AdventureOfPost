package com.group0562.adventureofpost.sudoku;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Board {

    /**
     * The number of rows the board has.
     */
    private int rows;

    /**
     * The number of columns the board has.
     */
    private int cols;

    /**
     * The number of hints left.
     */
    private int hintsLeft = 3;

    /**
     * A 2-D array of Cell instances representing the virtual board.
     */
    private Cell[][] board;

    /**
     * Constructor with preloaded board.
     *
     * @param board  the 2-D array representing the preloaded board.
     * @param rowDim the number of rows of the preloaded board.
     * @param colDim the number of columns of the preloaded board.
     */
    public Board(int[][] board, int rowDim, int colDim) {
        rows = rowDim;
        cols = colDim;
        this.board = new Cell[rowDim][colDim];

        loadBoard(board);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                result.append(getCell(row, col).getValue() + ' ');
            }
            result.append('\n');
        }
        return result.toString();
    }

    /**
     * Loads a given board as current game board.
     *
     * @param board a 2-D int array representing the values of each cell.
     */
    private void loadBoard(int[][] board) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell cell = new Cell(board[row][col], board[row][col] != 0);
                this.board[row][col] = cell;
            }
        }
    }

    /**
     * Access cell at (row, col) on the game board.
     *
     * @param row the row number.
     * @param col the column number.
     * @return the Cell instance at given location.
     */
    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    /**
     * Insert given number for cell at given location.
     *
     * @param row   the row number.
     * @param col   the column number.
     * @param input the new number of the cell.
     * @return a boolean indicating whether the insertion was successful or not.
     */
    public boolean insertNum(int row, int col, int input) {
        if (!board[row][col].isLocked()) {
            board[row][col].setValue(input);
            return true;
        }

        return false;
    }

    /**
     * Remove the value from the given cell on the board.
     *
     * @param row the row number.
     * @param col the column number.
     */
    public void removeNum(int row, int col) {
        if (!board[row][col].isLocked()) {
            board[row][col].setValue(0);
        }
    }

    /**
     * Resets the entire board to original state.
     *
     * @return a list of (rows, cols) that need to be updated.
     */
    public List<int[]> resetBoard() {
        List<int[]> resetCells = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!getCell(row, col).isLocked() && getCell(row, col).getValue() != 0) {
                    getCell(row, col).setValue(0);
                    resetCells.add(new int[]{row, col});
                }
            }
        }
        return resetCells;
    }

    // returns a hint to the user, by giving one square everytime a hint is requested.
    void hint() {
        if (hintsLeft > 0) {
            hintsLeft--;
        }
    }

    // finishes the entire board for the user. Ends the game.
    void finishBoard() {

    }

    /**
     * Check conflict with other cells whenever a new number is inserted
     *
     * @param input the number inserted.
     * @param row   the row number.
     * @param col   the column number.
     * @return a bool indicating whether there is conflict or not.
     */
    private boolean checkConflict(int input, int row, int col) {
        // TODO: fix index out of bounds issue, most likely in checkRegionConflict()
        return (checkHorizConflict(input, row, col) | checkRegionConflict(input, row, col) |
                checkVertConflict(input, row, col));
    }

    /**
     * Helper method that checks whether there is a horizontal conflict among the user input.
     *
     * @param input the number inserted.
     * @param row   the row number.
     * @param col   the column number.
     * @return a bool indicating whether there is horizontal conflict or not.
     */
    private boolean checkHorizConflict(int input, int row, int col) {
        for (int column = 0; column < cols; column++) {
            if (column == col && board[row][column].getValue() == input) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method that checks whether there is a vertical conflict among the user input.
     *
     * @param input the number inserted.
     * @param row   the row number.
     * @param col   the column number.
     * @return a bool indicating whether there is vertical conflict or not.
     */
    private boolean checkVertConflict(int input, int row, int col) {
        for (int currRow = 0; currRow < rows; currRow++) {
            if (currRow == row && board[currRow][col].getValue() == input) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method that checks whether there is a region conflict among the user input.
     *
     * @param input the number inserted.
     * @param row   the row number.
     * @param col   the column number.
     * @return a bool indicating whether there is region conflict or not.
     */
    private boolean checkRegionConflict(int input, int row, int col) {
        int horReg = (row - 1) / 2;
        int verReg = (col - 1) / 2;
        for (int currow = horReg * 2 - 1; currow < horReg * 2 + 1; currow++) {
            for (int curcol = verReg * 2 - 1; curcol < verReg * 2 + 1; curcol++) {
                if (board[currow][curcol].getValue() == input) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check whether the board is full or not.
     *
     * @return a bool indicating whether the board is full or not.
     */
    boolean checkFull() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
