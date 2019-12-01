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
            ArrayList<Long> numberlist = new ArrayList<Long>();
            ArrayList<Integer> indexlist = new ArrayList<Integer>();
            while(!cursor.isAfterLast()) {
                int index = cursor.getColumnIndex("points");
                indexlist.add(index);
                long n =  cursor.getLong(index);
                numberlist.add(n);
                cursor.moveToNext();
            }
            int maxindex = 0;
            long max = numberlist.get(0);
            for ( int x =0; x<indexlist.size(); x++) {
                if (numberlist.get(x) > max)
                    maxindex = x;
                    max = numberlist.get(x);
            }

            cursor.moveToFirst();
            String player = cursor.getString(maxindex);
            Result.add(player);
            Result.add(Long.toString(max));
        }
        cursor.close();
        return Result;
    }
}
