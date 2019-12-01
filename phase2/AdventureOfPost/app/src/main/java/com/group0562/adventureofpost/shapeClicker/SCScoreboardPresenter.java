package com.group0562.adventureofpost.shapeClicker;

//import java.sql.DatabaseMetaData;

import com.group0562.adventureofpost.database.DatabaseHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.ArrayList;

public class SCScoreboardPresenter {
    //private DatabaseMetaData database;

    private long board;
    private SQLiteDatabase db;


    public ArrayList getTopPlayer(){
        Cursor cursor = db.rawQuery("SELECT * FROM gameScores", null);
        ArrayList<String>Result = new ArrayList<String>();
        if (cursor.moveToFirst()){
            int pointsIndex = cursor.getColumnIndex("points");
            int userIndex = cursor.getColumnIndex("username");
            long max = cursor.getLong(pointsIndex);
            String topPlayer = cursor.getString(userIndex);
            while(!cursor.isAfterLast()) {
                long n =  cursor.getLong(pointsIndex);
                if(cursor.getLong(pointsIndex)>max) {
                    max = cursor.getLong(pointsIndex);
                    topPlayer = cursor.getString(userIndex);
                }
                cursor.moveToNext();
            }
            cursor.moveToFirst();
            Result.add(topPlayer);
            Result.add(Long.toString(max));
        }
        cursor.close();
        return Result;
    }
}
