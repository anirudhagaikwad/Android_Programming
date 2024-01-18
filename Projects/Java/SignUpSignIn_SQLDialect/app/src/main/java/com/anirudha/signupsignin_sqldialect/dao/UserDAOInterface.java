package com.anirudha.signupsignin_sqldialect.dao;

import java.util.List;

public interface UserDAOInterface {
    void saveUser(User user);
    User getUserById(long userId);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(long userId);
}
