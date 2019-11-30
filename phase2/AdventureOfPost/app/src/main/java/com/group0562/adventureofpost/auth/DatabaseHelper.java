package com.group0562.adventureofpost.auth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    DatabaseHelper(Context context) {
        super(context, "adventureOfPost.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (username text primary key, password text)");
        db.execSQL("CREATE TABLE gameScores (username text primary key, level integer, " +
                "time integer, conflicts integer, moves integer, composite integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    long insertScore(String username, int level, long time, int conflicts, int moves, int composite){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("time", (int)time);
        contentValues.put("conflicts", conflicts);
        contentValues.put("moves", moves);
        contentValues.put("composite", composite);
        return db.insert("gameScores", null, contentValues);
    }



    long insert(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        return db.insert("users", null, contentValues);
    }

    boolean checkUsernameDup(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=?", new String[]{username});
        return cursor.getCount() <= 0;
    }

    boolean verify(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username =? AND password=?", new String[]{username, password});
        return cursor.getCount() > 0;
    }
}
