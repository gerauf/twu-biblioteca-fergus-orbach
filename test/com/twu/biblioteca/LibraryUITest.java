package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by fergyo on 16/12/2016.
 */
public class LibraryUITest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStreamBuilder stubbedInput = new InputStreamBuilder();
    private final Library mockedLibrary = mock(Library.class);

    private final String LIST_BOOKS = "1";
    private final String LIST_MOVIES = "2";
    private final String CHECKOUT_ITEM = "3";
    private final String RETURN_ITEM = "4";
    private final String QUIT = "Q";
    private final String ITEM_NAME = "Awesome book";
    private final String MENU = "What action would you like to perform today?\n" +
            "1. List books\n" +
            "2. List movies\n" +
            "3. Checkout item\n" +
            "4. Return item\n" +
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
        System.setIn(stubbedInput.toReturn(QUIT).atSomePoint());

        LibraryUI libraryUI = new LibraryUI(mockedLibrary);
        libraryUI.start();

        assertThat(outContent.toString(), containsString("Hello and welcome to the Biblioteca"));
    }

    @Test
    public void displaysMainMenuOnInit() {
        System.setIn(stubbedInput.toReturn(QUIT).atSomePoint());

        LibraryUI libraryUI = new LibraryUI(mockedLibrary);
        libraryUI.start();

        assertThat(outContent.toString(), containsString(MENU));
    }

    @Test
    public void libraryAppQuitsWhenUserChoosesQFromMenu() {
        System.setIn(stubbedInput.toReturn(QUIT).atSomePoint());

        String exitMessage = "Thank you for visiting biblioteca";

        LibraryUI libraryUI = new LibraryUI(mockedLibrary);
        libraryUI.start();

        assertThat(outContent.toString(), containsString(exitMessage));
    }

    @Test
    public void menuItem1ListsItems() {
        System.setIn(stubbedInput.toReturn(LIST_BOOKS).then(QUIT).atSomePoint());

        LibraryUI libraryUI = new LibraryUI(mockedLibrary);
        libraryUI.start();

        verify(mockedLibrary).listAvailableItemsOfType(Book.class);
    }

    @Test
    public void menuItem2ListsMovies() {
        System.setIn(stubbedInput.toReturn(LIST_MOVIES).then(QUIT).atSomePoint());

        LibraryUI libraryUI = new LibraryUI(mockedLibrary);
        libraryUI.start();

        verify(mockedLibrary).listAvailableItemsOfType(Movie.class);
    }

    @Test
    public void failureMessageOnIncorrectMenuSelection() throws Exception {
        System.setIn(stubbedInput.toReturn("err").then(QUIT).atSomePoint());
        String menuError = "Sorry that is not a valid option";

        LibraryUI libraryUI = new LibraryUI(mockedLibrary);
        libraryUI.start();

        assertThat(outContent.toString(), containsString(menuError));
    }

    @Test
    public void menuItem3AllowsCustomersToCheckOutItem() {
        System.setIn(stubbedInput.toReturn(CHECKOUT_ITEM).then(ITEM_NAME).then(QUIT).atSomePoint());

        LibraryUI libraryUI = new LibraryUI(mockedLibrary);
        libraryUI.start();
        String checkoutMsg = "Please enter the title of the item you would like to checkout";

        assertThat(outContent.toString(), containsString(checkoutMsg));
        verify(mockedLibrary).checkoutItem(ITEM_NAME);
    }

    @Test
    public void failureMessageOnIncorrectItemSelection() {
        System.setIn(stubbedInput.toReturn(CHECKOUT_ITEM).then(ITEM_NAME).then(QUIT).atSomePoint());
        when(mockedLibrary.checkoutItem(ITEM_NAME)).thenReturn(false);

        LibraryUI libraryUI = new LibraryUI(mockedLibrary);
        libraryUI.start();
        String checkoutMsg = "That selection is not available";

        assertThat(outContent.toString(), containsString(checkoutMsg));
    }

    @Test
    public void successMessageOnCheckout() {
        System.setIn(stubbedInput.toReturn(CHECKOUT_ITEM).then(ITEM_NAME).then(QUIT).atSomePoint());
        when(mockedLibrary.checkoutItem(ITEM_NAME)).thenReturn(true);

        LibraryUI libraryUI = new LibraryUI(mockedLibrary);
        libraryUI.start();

        String checkoutMessage = "Thank you! Enjoy your selection";

        assertThat(outContent.toString(), containsString(checkoutMessage));
    }

    @Test
    public void menuItem4AllowsCustomersToReturnItem() {
        System.setIn(stubbedInput.toReturn(RETURN_ITEM).then(ITEM_NAME).then(QUIT).atSomePoint());


        LibraryUI libraryUI = new LibraryUI(mockedLibrary);
        libraryUI.start();
        String returnMsg = "Please enter the title of the item you would like to return";

        assertThat(outContent.toString(), containsString(returnMsg));
        verify(mockedLibrary).returnItem(ITEM_NAME);

    }

    @Test
    public void successMessageOnReturnOfItem() {
        System.setIn(stubbedInput.toReturn(RETURN_ITEM).then(ITEM_NAME).then(QUIT).atSomePoint());
        when(mockedLibrary.returnItem(ITEM_NAME)).thenReturn(true);

        LibraryUI libraryUI = new LibraryUI(mockedLibrary);
        libraryUI.start();
        String returnMsg = "Thank you for returning the item.";

        assertThat(outContent.toString(), containsString(returnMsg));
    }

    @Test
    public void failureMessageIfReturnedItemNotPreviouslyCheckedOut() {
        System.setIn(stubbedInput.toReturn(RETURN_ITEM).then(ITEM_NAME).then(QUIT).atSomePoint());
        when(mockedLibrary.returnItem(ITEM_NAME)).thenReturn(false);

        LibraryUI libraryUI = new LibraryUI(mockedLibrary);
        libraryUI.start();
        String returnMsg = "That is not a valid item to return.";

        assertThat(outContent.toString(), containsString(returnMsg));
    }
}