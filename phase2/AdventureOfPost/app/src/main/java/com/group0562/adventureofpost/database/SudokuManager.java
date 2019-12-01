package com.group0562.adventureofpost.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

class SudokuManager {

    SudokuManager() {}

    long insertStats(SQLiteDatabase db, String username, long time, int conflicts, int moves) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("time", (int) time);
        contentValues.put("conflicts", conflicts);
        contentValues.put("moves", moves);
        return db.insert("sudokuStats", null, contentValues);
    }
}
