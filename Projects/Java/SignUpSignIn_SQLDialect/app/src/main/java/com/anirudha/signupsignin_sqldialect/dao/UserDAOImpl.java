package com.anirudha.signupsignin_sqldialect.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.anirudha.signupsignin_sqldialect.dbhelper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAOInterface {

    private final DBHelper dbHelper;

    public UserDAOImpl(Context context) {
        dbHelper = new DBHelper(context);
    }

    @Override
    public void saveUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());

        db.insert("users", null, values);
        db.close();
    }

    @Override
    public User getUserById(long userId) {
        User user = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("users", new String[]{"id", "username", "password"},
                "id=?", new String[]{String.valueOf(userId)}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                user = new User(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
            }
            cursor.close();
        }

        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("users", new String[]{"id", "username", "password"},
                "username=?", new String[]{username}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                user = new User(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
            }
            cursor.close();
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM users", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    User user = new User(
                            cursor.getLong(0),
                            cursor.getString(1),
                            cursor.getString(2)
                    );
                    userList.add(user);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return userList;
    }

    @Override
    public void updateUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());

        db.update("users", values, "id=?", new String[]{String.valueOf(user.getId())});
        db.close();
    }

    @Override
    public void deleteUser(long userId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("users", "id=?", new String[]{String.valueOf(userId)});
        db.close();
    }
}
