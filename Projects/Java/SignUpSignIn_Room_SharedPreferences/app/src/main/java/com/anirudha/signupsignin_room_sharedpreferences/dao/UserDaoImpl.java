package com.anirudha.signupsignin_room_sharedpreferences.dao;

import com.anirudha.signupsignin_room_sharedpreferences.databaseclass.AppDatabase;

import java.util.List;

public class UserDaoImpl implements UserDaoInterface{
    private final AppDatabase database;

    public UserDaoImpl(AppDatabase database) {
        this.database = database;
    }

    @Override
    public void insertUser(User user) {
        database.userDaoInterface().insertUser(user);
    }

    @Override
    public User getUserById(int userId) {
        return database.userDaoInterface().getUserById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return database.userDaoInterface().getUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return database.userDaoInterface().getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        database.userDaoInterface().updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        database.userDaoInterface().deleteUser(user);
    }
}