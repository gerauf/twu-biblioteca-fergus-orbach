package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fergyo on 03/01/2017.
 */
public class UserTest {

    private static User user = new User("Jim Bob", "jb@gmail.com", "07969762516");

    @Test
    public void usersHaveAName() {
        assertEquals("Jim Bob", user.getName());
    }

    @Test
    public void usersHaveAnEmailAddress() {
        assertEquals("jb@gmail.com", user.getEmail());

    }

    @Test
    public void usersHaveAPhoneNumber() {
        assertEquals("07969762516", user.getPhoneNumber());

    }
}