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

    long insertStats(SQLiteDatabase db, String username, int stat1, int stat2, int stat3,
                     String stat1Name, String stat2Name, String stat3Name, String tableName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put(stat1Name, stat1);
        contentValues.put(stat2Name, stat2);
        contentValues.put(stat3Name, stat3);
        return db.insert(tableName, null, contentValues);
    }

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

    Map<String, Map<String, List<Integer>>> sudokuGlobalStats(SQLiteDatabase db) {
        String sql = "SELECT * FROM sudokuStats";
        try (Cursor cursor = db.rawQuery(sql, new String[]{})) {

            Map<String, Map<String, List<Integer>>> result = new HashMap<>();

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String username = cursor.getString(cursor.getColumnIndex("username"));
                    if (!result.containsKey(username)) {
                        Map<String, List<Integer>> userResult = new HashMap<>();
                        userResult.put("time", new ArrayList<>());
                        userResult.put("moves", new ArrayList<>());
                        userResult.put("conflicts", new ArrayList<>());
                        result.put(username, userResult);
                    }

                    result.get(username).get("time").add(cursor.getInt(cursor.getColumnIndex("time")));
                    result.get(username).get("moves").add(cursor.getInt(cursor.getColumnIndex("moves")));
                    result.get(username).get("conflicts").add(cursor.getInt(cursor.getColumnIndex("conflicts")));
                    cursor.moveToNext();
                }
            }

            for (Map<String, List<Integer>> entry : result.values()) {
                Collections.sort(entry.get("moves"), Collections.reverseOrder());
                Collections.sort(entry.get("time"), Collections.reverseOrder());
                Collections.sort(entry.get("conflicts"), Collections.reverseOrder());
            }

            return result;
        }
    }
}
