package com.anirudha.signupsignin_sqldialect.dao;

public class User {
    private long id;
    private String username;
    private String password;

    //Constructor
     public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    //getters
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //setters
    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
