package com.group0562.adventureofpost.sudoku;

public class Cell {

    /**
     * The value stored in the cell (0 means empty)
     */
    private int value;

    /**
     * A flag to determine whether to highlight cell for error
     */
    private boolean highlight = false;

    /**
     * A flag to determine whether the cell can be changed or not
     */
    private boolean locked;

    /**
     * Default constructor for empty cell.
     */
    public Cell() {
        this(0, false);
    }

    /**
     * Value constructor.
     *
     * @param value the value of the cell.
     * @param locked whether the cell is locked or not.
     */
    public Cell(int value, boolean locked) {
        this.value = value;
        this.locked = locked;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public boolean isLocked() {
        return locked;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
