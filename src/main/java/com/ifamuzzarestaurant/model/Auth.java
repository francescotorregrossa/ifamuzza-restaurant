package com.ifamuzzarestaurant.model;

public class Auth {

    private User user;
    private static Auth instance;

    public static Auth getInstance() {
        if (instance == null) {
            instance = new Auth();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

}