package com.group0562.adventureofpost.sudoku;

import java.util.Observable;


/**
 * A virtual game board for Sudoku game.
 */
class Board extends Observable {

    /**
     * The number of rows the board has.
     */
    private int rows;

    /**
     * The number of columns the board has.
     */
    private int cols;

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
    Board(int[][] board, int rowDim, int colDim) {
        rows = rowDim;
        cols = colDim;
        this.board = new Cell[rowDim][colDim];

        loadBoard(board);
    }

    Board(int[][] puzzleBoard, int[][] lockedBoard, int rowDim, int colDim) {
        rows = rowDim;
        cols = colDim;
        this.board = new Cell[rowDim][colDim];

        savedLoadBoard(puzzleBoard, lockedBoard);
    }

    private void savedLoadBoard(int[][] puzzleBoard, int[][] lockedBoard) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                boolean isLocked;
                isLocked = lockedBoard[row][col] != 1;
                Cell cell = new Cell(puzzleBoard[row][col], isLocked);
                this.board[row][col] = cell;
            }
        }

        setChanged();
        notifyObservers();
    }

    int getDim() {
        return rows;
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

        setChanged();
        notifyObservers();
    }

    /**
     * Generates a string containing all numbers on the board, and also whether the cell is locked.
     *
     * @return the string representation of board data.
     */
    String getBoardData() {
        StringBuilder strBoard = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                strBoard.append(board[row][col].getValue());
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col].isLocked()) {
                    strBoard.append(0);
                } else {
                    strBoard.append(1);
                }
            }
        }
        return strBoard.toString();
    }

    /**
     * Access cell at (row, col) on the game board.
     *
     * @param row the row number.
     * @param col the column number.
     * @return the Cell instance at given location.
     */
    Cell getCell(int row, int col) {
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
    boolean insertNum(int row, int col, int input) {
        if (input == 0 | !checkConflict(input, row, col)) {
            board[row][col].setValue(input);
            return true;
        }

        setChanged();
        notifyObservers();

        return false;
    }

    /**
     * Resets the entire board to original state.
     */
    void resetBoard() {
        // Reset board
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!board[row][col].isLocked() && board[row][col].getValue() != 0) {
                    board[row][col].setValue(0);
                }
            }
        }

        setChanged();
        notifyObservers();
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
        return (checkHorizConflict(input, row, col) | checkVertConflict(input, row, col) |
                checkRegionConflict(input, row, col));
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
        for (int y = 0; y < cols; y++) {
            if (board[row][y].getValue() == input && y != col) {
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
        for (int x = 0; x < rows; x++) {
            if (board[x][col].getValue() == input && x != row) {
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
        int horReg = col / 3;

        int verFactor = (rows == 6) ? 2 : 3;
        int verReg = row / verFactor;

        for (int x = verReg * verFactor; x < verReg * verFactor + verFactor; x++) {
            for (int y = horReg * 3; y < horReg * 3 + 3; y++) {
                if (board[x][y].getValue() == input && (x != row && y != col)) {
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

    /**
     * Sudoku Board Cell Class
     */
    class Cell {

        /**
         * The value stored in the cell (0 means empty)
         */
        private int value;

        /**
         * A flag to determine whether the cell can be changed or not
         */
        private boolean locked;

        /**
         * Value constructor.
         *
         * @param value  the value of the cell.
         * @param locked whether the cell is locked or not.
         */
        Cell(int value, boolean locked) {
            this.value = value;
            this.locked = locked;
        }

        boolean isLocked() {
            return locked;
        }

        int getValue() {
            return value;
        }

        void setValue(int value) {
            this.value = value;
        }
    }
}
