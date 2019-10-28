package com.group0562.adventureofpost.sudoku;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SudokuTest {

    @Test
    public void updatePoints() {
    }

    @Test
    public void checkComplete() {
    }

    @Test
    public void getRandomPuzzle() {
        Sudoku inst = new Sudoku();
        int[][] puzzle = inst.getRandomPuzzle();

        for (int[] row: puzzle) {
            System.out.println(Arrays.toString(row));
            assertEquals(6, row.length);
        }
    }
}