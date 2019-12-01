package com.group0562.adventureofpost.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class AuthManager {

    long insertAuth(SQLiteDatabase db, String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        return db.insert("users", null, contentValues);
    }

    boolean checkUsernameDup(SQLiteDatabase db, String username) {
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=?", new String[]{username});
        boolean result = cursor.getCount() <= 0;
        cursor.close();
        return result;
    }

    boolean verify(SQLiteDatabase db, String username, String password) {
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }
}
