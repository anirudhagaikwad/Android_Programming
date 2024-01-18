package com.anirudha.signupsignin_room_sharedpreferences.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface UserDaoInterface {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE uid = :userId")
    User getUserById(int userId);

    @Query("SELECT * FROM users WHERE user_name = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);
}
