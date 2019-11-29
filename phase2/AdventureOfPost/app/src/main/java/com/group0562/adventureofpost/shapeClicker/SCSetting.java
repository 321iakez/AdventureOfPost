package com.group0562.adventureofpost.shapeClicker;

public class SCSetting {
    static String color;
    static String shape;
    static String difficulty;
    static String mode;
    public SCSetting(){
        SCSetting.color = "Black";
        SCSetting.shape = "Circle";
        SCSetting.difficulty = "Easy";
        SCSetting.mode = "Normal";
    }
    public static void setColor(String color){
        SCSetting.color = color;
    }
    public static void setShape(String shape){
        SCSetting.shape = shape;
    }
    public static void setDifficulty(String difficulty){
        SCSetting.difficulty = difficulty;
    }
    public static void setMode(String mode){
        SCSetting.mode = mode;
    }
    static String getColor(){return SCSetting.color;}
    static String getShape(){return SCSetting.shape;}
    static String getDifficulty(){return SCSetting.difficulty;}
    static String getMode(){return SCSetting.mode;}
}
