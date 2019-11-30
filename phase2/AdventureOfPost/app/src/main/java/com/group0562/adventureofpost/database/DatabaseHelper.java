package com.group0562.adventureofpost.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.group0562.adventureofpost.shapeClicker.Shape;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private AuthManager authManager;
    private SudokuManager sudokuManager;
    private ShapeClickerManager shapeClickerManager;
    private TriviaManager triviaManager;

    public DatabaseHelper(Context context) {
        super(context, "adventureOfPost.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        authManager = new AuthManager();
        sudokuManager = new SudokuManager();
        shapeClickerManager = new ShapeClickerManager();
        triviaManager = new TriviaManager();

        db.execSQL("CREATE TABLE users (username TEXT primary key, password TEXT)");
        db.execSQL("CREATE TABLE sudokuStats (time INTEGER, conflicts INTEGER, moves INTEGER," +
                "FOREIGN KEY (username) REFERENCES users (username))"); // time, conflicts, moves
        db.execSQL("CREATE TABLE triviaStats (correct INTEGER, incorrect INTEGER, score INTEGER, " +
                "FOREIGN KEY (username) REFERENCES users (username))"); // correct, incorrect, score
        db.execSQL("CREATE TABLE shapeClickerStats (time INTEGER, points INTEGER, lives INTEGER," +
                "FOREIGN KEY (username) REFERENCES users (username))"); // time, points, lives
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS sudokuStats");
        db.execSQL("DROP TABLE IF EXISTS triviaStats");
        db.execSQL("DROP TABLE IF EXISTS shapeClickerStats");
        onCreate(db);
    }

    public long insertSudokuStats(String username, long time, int conflicts, int moves) {
        SQLiteDatabase db = getWritableDatabase();
        return sudokuManager.insertStats(db, username, time, conflicts, moves);
    }

    public long insertTriviaStats(String username, int correct, int incorrect, int score) {
        SQLiteDatabase db = getWritableDatabase();
        return triviaManager.insertStats(db, username, correct, incorrect, score);
    }

    public long insertShapeClickerStats(String username, long time, int points, int lives) {
        SQLiteDatabase db = getWritableDatabase();
        return shapeClickerManager.insertStats(db, username, time, points, lives);
    }

    public long insertAuth(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        return authManager.insertAuth(db, username, password);
    }

    public boolean checkUsernameDup(String username) {
        SQLiteDatabase db = getReadableDatabase();
        return authManager.checkUsernameDup(db, username);
    }

    public boolean verify(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        return authManager.verify(db, username, password);
    }
}
