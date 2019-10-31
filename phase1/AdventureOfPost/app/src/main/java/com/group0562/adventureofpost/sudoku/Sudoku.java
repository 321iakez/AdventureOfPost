package com.group0562.adventureofpost.sudoku;

import com.group0562.adventureofpost.Puzzles;

import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class Sudoku extends Puzzles {

    private Board gameBoard;

    public Sudoku(InputStream file) {
        super(new SudokuStats(100000));

        gameBoard = new Board(getRandomPuzzle(file), 6, 6);
    }

    /**
     * Randomly selects a puzzle from the puzzles
     *
     * @return a 2-D integer array puzzle.
     */
    public int[][] getRandomPuzzle(InputStream file) {
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
     * since every user input must follow not have any conflicts with the existing board, the game
     * if complete iff the board is full.
     */
    @Override
    public void checkComplete() {
        boolean result = gameBoard.checkFull();
    }

    public Board getGameBoard() {
        return gameBoard;
    }
}
