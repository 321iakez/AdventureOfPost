package com.group0562.adventureofpost.sudoku;

import com.group0562.adventureofpost.Puzzles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Sudoku extends Puzzles {

    private Board gameBoard;

    public Sudoku() {
        super(new SudokuStats(100000));

        gameBoard = new Board(getRandomPuzzle(), 6, 6);
    }

    /**
     * Randomly selects a puzzle from the puzzles
     *
     * @return a 2-D integer array puzzle.
     */
    public int[][] getRandomPuzzle() {
        // Read file
        File f = new File("src/main/java/com/group0562/adventureofpost/sudoku/sudoku.csv");
        Random rand = new Random();

        Scanner scanner = null;
        try {
            scanner = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
}
