package com.group0562.adventureofpost.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class AuthManager {

    /**
     * Insert given username and password into the database.
     *
     * @param db       the database to insert info to.
     * @param username the user input username.
     * @param password the user input password.
     * @return the row number in the table that the data is inserted into.
     */
    long insertAuth(SQLiteDatabase db, String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        return db.insert("users", null, contentValues);
    }

    /**
     * Check whether user input username already exists in database.
     *
     * @param db       the database to check duplicate from.
     * @param username the user input username.
     * @return a boolean indicating whether the username is duplicate.
     */
    boolean checkUsernameDup(SQLiteDatabase db, String username) {
        String sql = "SELECT * FROM users WHERE username=?";
        try (Cursor cursor = db.rawQuery(sql, new String[]{username})) {
            return cursor.getCount() <= 0;
        }
    }

    /**
     * Verify user input username and password.
     *
     * @param db       the database to verify credentials.
     * @param username the user input username.
     * @param password the user input password.
     * @return a boolean indicating whether the credential exists.
     */
    boolean verify(SQLiteDatabase db, String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Cursor cursor = db.rawQuery(sql, new String[]{username, password})) {
            return cursor.getCount() > 0;
        }
    }
}
