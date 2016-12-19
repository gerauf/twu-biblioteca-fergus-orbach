package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by fergyo on 16/12/2016.
 */
public class LibraryUITest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    Library lib = new Library();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }


    @Test
    public void welcomeMessageOnInit() {
        new LibraryUI();

        assertThat(outContent.toString(), CoreMatchers.containsString("Hello and welcome to the Biblioteca"));
    }

    @Test
    public void displaysMainMenuOnInit() {
        String menuHeader = "What action would you like to perform today?";
        String menuItem1 = "1. List books";
        String menuSelector = "Type the number of the desired menu item and press enter";

        new LibraryUI();

        assertThat(outContent.toString(), CoreMatchers.containsString(menuHeader));
        assertThat(outContent.toString(), CoreMatchers.containsString(menuItem1));
        assertThat(outContent.toString(), CoreMatchers.containsString(menuSelector));

    }

    @Test
    public void menuItemOneListsBooks() {
        LibraryUI libUI = new LibraryUI();
        String list = lib.list();
        assertThat(libUI.selectMenu(1), CoreMatchers.containsString(list));

    }

    @Test
    public void incorrectSelectionReturnsErrorMessage() throws Exception {
        LibraryUI libUI = new LibraryUI();
        String list = lib.list();
        assertThat(libUI.selectMenu(1), CoreMatchers.containsString(list));

    }
}