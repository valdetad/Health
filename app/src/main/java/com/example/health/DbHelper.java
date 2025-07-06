package com.example.health;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "health.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DbHelper";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USERS_TABLE = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL, " +
                "password TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_USERS_TABLE);
        Log.d(TAG, "Database created with table: users");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public long addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long userId = -1;
        try {
            userId = db.insert("users", null, values);
            Log.d(TAG, "User added with id: " + userId);
        } catch (Exception e) {
            Log.e(TAG, "Error inserting user", e);
        }
        return userId;
    }
}
