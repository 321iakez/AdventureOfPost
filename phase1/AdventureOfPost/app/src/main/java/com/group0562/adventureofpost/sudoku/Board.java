package com.group0562.adventureofpost.sudoku;

import androidx.annotation.NonNull;

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

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    // allows insertion of numbers into the board, but only if
    public boolean insertNum(int row, int col, int input) {
        if (!board[row][col].isLocked()) {
            board[row][col].setValue(input);
            return true;
        }

        return false;
    }

    // delete the entry from a slot in the board
    void removeNum(int row, int col) {
        if (!board[row][col].isLocked()) {
            board[row][col].setValue(0);
        }
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

    private boolean checkConflict(int input, int row, int col) {
        // TODO: fix index out of bounds issue, most likely in checkRegionConflict()
        return (!checkHorizConflict(input, row, col) & !checkRegionConflict(input, row, col) &
                !checkVertConflict(input, row, col));
    }

    // helper method that checks whether there is a horizontal conflict among the user input.
    private boolean checkHorizConflict(int input, int row, int col) {
        for (int column = 0; column < cols; column++) {
            if (column == col) {
            } else {
                if (board[row][column].getValue() == input) {
                    return true;
                }
            }
        }
        return false;
    }

    // helper method that checks for vertical conflicts among the user input.
    private boolean checkVertConflict(int input, int row, int col) {
        for (int currow = 0; currow < rows; currow++) {
            if (currow == row) {
            } else {
                if (board[currow][col].getValue() == input) {
                    return true;
                }
            }
        }
        return false;
    }

    // helper method that checks for region conflict among the user input.
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
