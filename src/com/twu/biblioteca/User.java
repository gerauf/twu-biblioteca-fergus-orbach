package com.twu.biblioteca;

/**
 * Created by fergyo on 03/01/2017.
 */
public class User {

    private String name, email, phoneNumber;

    User(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
