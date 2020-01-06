package com.group0562.adventureofpost.shapeClicker;

/**
 * this class is for a easier access of the setting the user chose, it is meant to be accessed by
 * the model classes to store and get values they needed to know to set up the classes
 */
public class SCSetting {

    private static String color;
    private static String shape;
    private static String difficulty;
    private static String mode;
    private static String username;

    public SCSetting() {
        SCSetting.color = "Black";
        SCSetting.shape = "Circle";
        SCSetting.difficulty = "Easy";
        SCSetting.mode = "Normal";
        SCSetting.username = "";
    }


    /**
     * getters and setters for this class
     */
    public static void setColor(String color) {
        SCSetting.color = color;
    }

    public static void setShape(String shape) {
        SCSetting.shape = shape;
    }

    public static void setDifficulty(String difficulty) {
        SCSetting.difficulty = difficulty;
    }

    public static void setMode(String mode) {
        SCSetting.mode = mode;
    }

    public static void setUsername(String username) {
        SCSetting.username = username;
    }

    public static String getColor() {
        return SCSetting.color;
    }

    public static String getShape() {
        return SCSetting.shape;
    }

    public static String getDifficulty() {
        return SCSetting.difficulty;
    }

    public static String getMode() {
        return SCSetting.mode;
    }

    public static String getUsername() {
        return SCSetting.username;
    }
}
