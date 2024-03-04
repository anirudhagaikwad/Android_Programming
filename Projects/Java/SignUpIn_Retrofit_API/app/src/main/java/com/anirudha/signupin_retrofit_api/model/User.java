package com.anirudha.signupin_retrofit_api.model;

public class User {

    private int id;
    private String username;
    private String mobile;
    private String email;
    private String address;

    public User(String username, String mobile, String email, String address) {
        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
    }


    public User(int id, String username, String mobile, String email, String address) {
        this.id = id;
        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

