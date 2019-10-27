package com.group0562.adventureofpost.sudoku;

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
     * A 2-D array of Cell instances representing the virtual board.
     */
    private Cell[][] board;

    /**
     * Default constructor.
     */
    public Board() {
        rows = 6;
        cols = 6;
        board = new Cell[6][6];
    }

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

    /**
     * Loads a given board as current game board.
     *
     * @param board a 2-D int array representing the values of each cell.
     */
    private void loadBoard(int[][] board) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell cell = new Cell(board[row][col], board[row][col] == 0);
                this.board[row][col] = cell;
            }
        }
    }

    // allows insertion of numbers into the board
    void insertNum(int row, int col, int input){

    }

    // delete the entry from a slot in the board
    void removeNum(int row, int col){

    }

    // returns a hint to the user, by giving one square everytime a hint is requested.
    void hint(){

    }

    // finishes the entire board for the user. Ends the game.
    void finishBoard(){

    }

    // helper method that checks whether there is a horizontal conflict among the user input.
    void checkHorizConflict(){

    }

    // helper method that checks for vertical conflicts among the user input.
    void checkVertConflict(){

    }

    // helper method that checks for region conflict among the user input.
    void checkRegionConflict(){

    }



    }
