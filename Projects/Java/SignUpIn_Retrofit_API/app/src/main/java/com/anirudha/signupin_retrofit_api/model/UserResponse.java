package com.anirudha.signupin_retrofit_api.model;

public class UserResponse {

    private boolean error;
    private String message;
    private User user;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}

