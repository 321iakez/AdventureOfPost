package com.group0562.adventureofpost.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    DatabaseHelper(Context context) {
        super(context, "adventureOfPost.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users (username text primary key, password text)");
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        ArrayList<String> arrTblNames = new ArrayList<String>();
        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                arrTblNames.add( c.getString( c.getColumnIndex("name")) );
                c.moveToNext();
            }
        }
        Log.i("Database", arrTblNames.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    boolean insert(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long ins = db.insert("users", null, contentValues);
        return ins != 1;
    }

    boolean checkUsernameDup(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=?", new String[]{username});
        return cursor.getCount() <= 0;
    }

    Boolean verify(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username =? AND password=?", new String[]{username, password});
        return cursor.getCount() > 0;
    }
}
