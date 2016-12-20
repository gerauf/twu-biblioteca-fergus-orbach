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
    private final InputStreamBuilder input = new InputStreamBuilder();
    private Library lib = new Library();


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

        assertThat(outContent.toString(), CoreMatchers.containsString("Hello and welcome to the Biblioteca"));
    }



        @Test
        public void displaysMainMenuOnInit() {

            System.setIn(input.toReturn("Q").atSomePoint());

            String menu = "What action would you like to perform today?\n" +
                    "1. List books\n" +
                    "Q. Quit program\n" +
                    "Type the number of the desired menu item and press enter";

            new LibraryUI(lib);

            assertThat(outContent.toString(), CoreMatchers.containsString(menu));


        }

        @Test
        public void libraryAppQuitsWhenUserChoosesQFromMenu() throws Exception {
            System.setIn(input.toReturn("Q").atSomePoint());

            String exitMessage = "Thank you for visiting biblioteca";

            new LibraryUI(lib);

            assertThat(outContent.toString(), CoreMatchers.containsString(exitMessage));

        }

        @Test
        public void menuItemOneListsBooks() {
            System.setIn(input.toReturn("1").then("Q").atSomePoint());

            new LibraryUI(lib);
            String list = lib.list();

            assertThat(outContent.toString(), CoreMatchers.containsString(list));

        }

        @Test
        public void incorrectSelectionReturnsErrorMessage() throws Exception {
            System.setIn(input.toReturn("err").then("Q").atSomePoint());
            String menuError = "Sorry that is not a valid option";

            new LibraryUI(lib);

            assertThat(outContent.toString(), CoreMatchers.containsString(menuError));

        }

 
}