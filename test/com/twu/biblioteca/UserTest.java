package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fergyo on 03/01/2017.
 */
public class UserTest {

    private static String USER_ID = "123-4567";
    private static String PASSWORD = "passw0rd";
    private static String FORMAT = "%-30.30s  %-30.30s %-11.11s\n";
    private static String NAME = "Jim Bob";
    private static String EMAIL = "jb@gmail.com";
    private static String PHONE = "07969762516";
    private static User USER = new User(NAME, EMAIL, PHONE, USER_ID, PASSWORD);
    private static String USER_DETAILS = String.format(FORMAT, NAME, EMAIL, PHONE);

    @Test
    public void usersHaveAName() {
        assertEquals("Jim Bob", USER.getName());
    }

    @Test
    public void usersHaveAnEmailAddress() {
        assertEquals("jb@gmail.com", USER.getEmail());
    }

    @Test
    public void usersHaveAPhoneNumber() {
        assertEquals("07969762516", USER.getPhoneNumber());
    }

    @Test
    public void checkUserIDReturnsTrueIfCorrectIDSupplied() {
        assertTrue(USER.checkUser(USER_ID));
    }

    @Test
    public void checkPasswordDReturnsTrueIfCorrectIDAndPasswordSupplied() {
        assertTrue(USER.checkPassword(USER_ID, PASSWORD));
    }

    @Test
    public void checkPasswordDReturnsFalseIfCorrectIDButWrongPasswordSupplied() {
        assertFalse(USER.checkPassword(USER_ID, "wrong password"));
    }

    @Test
    public void toStringPrintsOutNameEmailAndPhoneInColumnsFormat() {
        assertEquals(USER_DETAILS, USER.toString());
    }
}