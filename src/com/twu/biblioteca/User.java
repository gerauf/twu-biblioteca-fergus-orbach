package com.twu.biblioteca;

/**
 * Created by fergyo on 03/01/2017.
 */
public class User {

    private String name, email, phoneNumber, id, password;
    private static String FORMAT = "%-30.30s  %-30.30s %-11.11s\n";


    User(String name, String email, String phoneNumber, String id, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.password = password;
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

    public boolean checkUser(String id) {
        return id.equals(this.id);
    }

    public boolean checkPassword(String id, String password) {
        return checkUser(id) && this.password.equals(password);
    }

    @Override
    public String toString() {
        return String.format(FORMAT, name, email, phoneNumber);
    }
}
