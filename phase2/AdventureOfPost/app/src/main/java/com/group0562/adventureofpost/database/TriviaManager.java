package com.group0562.adventureofpost.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

class TriviaManager {

    TriviaManager() {
    }

    long insertStats(SQLiteDatabase db, String username, int correct, int incorrect, int score) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("correct", correct);
        contentValues.put("incorrect", incorrect);
        contentValues.put("score", score);
        return db.insert("triviaStats", null, contentValues);
    }
}
