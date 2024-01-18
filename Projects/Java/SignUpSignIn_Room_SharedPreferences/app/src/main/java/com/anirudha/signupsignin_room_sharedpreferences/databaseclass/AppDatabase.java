package com.anirudha.signupsignin_room_sharedpreferences.databaseclass;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.anirudha.signupsignin_room_sharedpreferences.dao.User;
import com.anirudha.signupsignin_room_sharedpreferences.dao.UserDaoInterface;

/*
getInstance is a method that returns the singleton instance of AppDatabase.
It checks if the instance is already created; if not, it creates a new one.
This way, you ensure that only one instance of the Room database is created for the entire application.
 */
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase instance;

    public abstract UserDaoInterface userDaoInterface();
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "mydb"
            ).build();
        }
        return instance;
    }
}
