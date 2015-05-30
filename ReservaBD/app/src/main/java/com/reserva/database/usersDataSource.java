package com.reserva.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.reserva.model.User;

/**
 * Created by larachicharo on 29/05/15.
 */
public class usersDataSource {
    // Database fields
    private SQLiteDatabase database;
    private userSQLHelper dbHelper;
    private String[] allColumns = {userSQLHelper.COLUMN_ID,
            userSQLHelper.COLUMN_USERNAME, userSQLHelper.COLUMN_PASSWORD };

    public usersDataSource(Context context) {
        dbHelper = new userSQLHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public User createUser(String username_, String password_) {
        ContentValues values = new ContentValues();
        values.put(userSQLHelper.COLUMN_USERNAME, username_);
        values.put(userSQLHelper.COLUMN_PASSWORD, password_);
        long insertId = database.insert(userSQLHelper.TABLE_USERS, null,
                values);
        Cursor cursor = database.query(userSQLHelper.TABLE_USERS,
                allColumns, userSQLHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    public User cursorToUser(Cursor cursor){
        User user = new User();
        if (cursor == null){
            user = null;
        } else {
            user.setId(cursor.getLong(0));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
        }
        return user;
    }

    public User getUser(String username, String password){
        Cursor cursor = database.query(userSQLHelper.TABLE_USERS,
                allColumns,
                userSQLHelper.COLUMN_USERNAME + " = " + username,
                null, null, null, null);
        cursor.moveToFirst();
        return cursorToUser(cursor);
    }
}
//userSQLHelper.COLUMN_USERNAME + " = " + username + " AND " + userSQLHelper.COLUMN_PASSWORD + " = " + password,