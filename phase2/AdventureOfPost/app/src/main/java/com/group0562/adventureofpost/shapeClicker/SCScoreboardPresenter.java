package com.group0562.adventureofpost.shapeClicker;

//import java.sql.DatabaseMetaData;

public class SCScoreboardPresenter {
    //private DatabaseMetaData database;

    private String first;
    private long points;
    SCScoreboardPresenter(){
        first = "Matthew";
        points = 100;
    }
    public String getFirst(){
        return first;
    }
    public long getPoints(){
        return points;
    }
}
