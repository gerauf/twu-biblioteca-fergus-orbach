package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by fergyo on 16/12/2016.
 */
public class LibraryUITest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStreamBuilder input = new InputStreamBuilder();
    private Library lib = new Library();
    private final String MENU = "What action would you like to perform today?\n" +
            "1. List books\n" +
            "2. Checkout book\n" +
            "Q. Quit program\n" +
            "Type the number of the desired menu item and press enter";


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setIn(null);
        System.setErr(null);
    }

    @Test
    public void welcomeMessageOnInit() {
        System.setIn(input.toReturn("Q").atSomePoint());

        new LibraryUI(lib);

        assertThat(outContent.toString(), containsString("Hello and welcome to the Biblioteca"));
    }

    @Test
    public void displaysMainMenuOnInit() {
        System.setIn(input.toReturn("Q").atSomePoint());

        new LibraryUI(lib);

        assertThat(outContent.toString(), containsString(MENU));
    }

    @Test
    public void libraryAppQuitsWhenUserChoosesQFromMenu() throws Exception {
        System.setIn(input.toReturn("Q").atSomePoint());

        String exitMessage = "Thank you for visiting biblioteca";

        new LibraryUI(lib);

        assertThat(outContent.toString(), containsString(exitMessage));
    }

    @Test
    public void menuItemOneListsBooks() {
        System.setIn(input.toReturn("1").then("Q").atSomePoint());

        new LibraryUI(lib);
        String list = lib.list();

        assertThat(outContent.toString(), containsString(list));
    }

    @Test
    public void failureMessageOnIncorrectMenuSelection() throws Exception {
        System.setIn(input.toReturn("err").then("Q").atSomePoint());
        String menuError = "Sorry that is not a valid option";

        new LibraryUI(lib);

        assertThat(outContent.toString(), containsString(menuError));
    }

    @Test
    public void menuItemTwoAllowsCustomersToCheckOutBook() {
        System.setIn(input.toReturn("2").then("Purity").then("Q").atSomePoint());

        new LibraryUI(lib);
        String checkoutMsg = "Please enter the title of the book you would like to checkout";

        assertThat(outContent.toString(), containsString(checkoutMsg));
    }

    @Test
    public void failureMessageOnIncorrectBookSelection() {
        System.setIn(input.toReturn("2").then("Puberty").then("Q").atSomePoint());

        new LibraryUI(lib);
        String checkoutMsg = "That book is not available";

        assertThat(outContent.toString(), containsString(checkoutMsg));
    }

    @Test
    public void successMessageOnCheckout() {
        String bookTitle = "Purity";
        String checkoutMessage = "Thank you! Enjoy the book";
        System.setIn(input.toReturn("2").then(bookTitle).then("Q").atSomePoint());


        new LibraryUI(lib);

        assertThat(outContent.toString(), containsString(checkoutMessage));
    }
}