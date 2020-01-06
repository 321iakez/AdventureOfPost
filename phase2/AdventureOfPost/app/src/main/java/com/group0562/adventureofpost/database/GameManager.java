package com.group0562.adventureofpost.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GameManager {

    /**
     * Insert given stats into given database.
     *
     * @param db        the database to store the stats.
     * @param username  the username of the user.
     * @param stat1     the first stats category.
     * @param stat2     the second stats category.
     * @param stat3     the third stats category
     * @param stat1Name the first stats category name.
     * @param stat2Name the second stats category name.
     * @param stat3Name the third stats category name.
     * @param tableName the name of the table to insert the stats in.
     * @return the row number in the table that the data is inserted into.
     */
    long insertStats(SQLiteDatabase db, String username, int stat1, int stat2, int stat3,
                     String stat1Name, String stat2Name, String stat3Name, String tableName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put(stat1Name, stat1);
        contentValues.put(stat2Name, stat2);
        contentValues.put(stat3Name, stat3);
        return db.insert(tableName, null, contentValues);
    }

    /**
     * Read from database and retrieve player stats to be displayed on score board.
     *
     * @param db        the database to read the stats.
     * @param username  the username of the user.
     * @param stat1     the first stats category.
     * @param stat2     the second stats category.
     * @param stat3     the third stats category.
     * @param tableName the table to read the stats from.
     * @return a map where the key is the name of stats category and value is a list of stats.
     */
    Map<String, List<Integer>> playerStats(SQLiteDatabase db, String username, String stat1, String stat2, String stat3, String tableName) {
        String sql = "SELECT * FROM " + tableName + " WHERE username=? ORDER BY id DESC LIMIT 10";
        try (Cursor cursor = db.rawQuery(sql, new String[]{username})) {

            List<Integer> stat1List = new ArrayList<>();
            List<Integer> stat2List = new ArrayList<>();
            List<Integer> stat3List = new ArrayList<>();

            // Retrieve all entries from the table for given user
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    stat1List.add(cursor.getInt(cursor.getColumnIndex(stat1)));
                    stat2List.add(cursor.getInt(cursor.getColumnIndex(stat2)));
                    stat3List.add(cursor.getInt(cursor.getColumnIndex(stat3)));

                    cursor.moveToNext();
                }
            }

            // Sort all stats in descending order
            Collections.sort(stat1List, Collections.reverseOrder());
            Collections.sort(stat2List, Collections.reverseOrder());
            Collections.sort(stat3List, Collections.reverseOrder());

            Map<String, List<Integer>> result = new HashMap<>();
            result.put(stat1, stat1List);
            result.put(stat2, stat2List);
            result.put(stat3, stat3List);

            return result;
        }
    }

    /**
     * Stores the stats of a game that will be resumable in the database.
     *
     * @param stats: a string that represents Sudoku stats of a game.
     * @return returns a long specifying if insert was successful
     */
    long insertResumeStats(SQLiteDatabase db, String stats) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", "resume_stats");
        contentValues.put("stats", stats);
        db.execSQL("delete from game_stats where id = ?", new String[]{"resume_stats"});
        return db.insert("game_stats", null, contentValues);
    }

    /**
     * This method stores the state of the sudoku game.
     *
     * @param gameId:    stores the type of game
     * @param username:  username of the player
     * @param gameState: string representing the state of the board
     * @return returns a long specifying if insert was successful
     */
    long insertGameState(SQLiteDatabase db, String gameId, String username, String gameState) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("gameId", gameId);
        contentValues.put("username", username);
        contentValues.put("gameState", gameState);
        db.execSQL("delete from game_saves where gameId = ? and username = ?", new String[]{gameId, username});
        return db.insert("game_saves", null, contentValues);
    }
}
