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
        String sql = "SELECT * FROM users WHERE username=?";
        try (Cursor cursor = db.rawQuery(sql, new String[]{username})) {
            return cursor.getCount() <= 0;
        }
    }

    boolean verify(SQLiteDatabase db, String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Cursor cursor = db.rawQuery(sql, new String[]{username, password})) {
            return cursor.getCount() > 0;
        }
    }
}
