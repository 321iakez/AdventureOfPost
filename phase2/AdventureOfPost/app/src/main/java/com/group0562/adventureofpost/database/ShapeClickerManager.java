package com.group0562.adventureofpost.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

class ShapeClickerManager {

    long insertStats(SQLiteDatabase db, String username, long time, int points, int lives) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("time", (int) time);
        contentValues.put("points", points);
        contentValues.put("lives", lives);
        return db.insert("gameScores", null, contentValues);
    }
}
