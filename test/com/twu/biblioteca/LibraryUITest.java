package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by fergyo on 16/12/2016.
 */
public class LibraryUITest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStreamBuilder input = new InputStreamBuilder();
    private final Library mockedLibrary = mock(Library.class);
    private final Book mockedBook = mock(Book.class);

    private final String LIST_BOOKS = "1";
    private final String CHECKOUT_BOOK = "2";
    private final String RETURN_BOOK = "3";
    private final String QUIT = "Q";
    private final String BOOK_NAME = "Awesome book";
    private final String MENU = "What action would you like to perform today?\n" +
            "1. List books\n" +
            "2. Checkout book\n" +
            "3. Return book\n" +
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
        System.setIn(input.toReturn(QUIT).atSomePoint());

        new LibraryUI(mockedLibrary);

        assertThat(outContent.toString(), containsString("Hello and welcome to the Biblioteca"));
    }

    @Test
    public void displaysMainMenuOnInit() {
        System.setIn(input.toReturn(QUIT).atSomePoint());

        new LibraryUI(mockedLibrary);

        assertThat(outContent.toString(), containsString(MENU));
    }

    @Test
    public void libraryAppQuitsWhenUserChoosesQFromMenu() {
        System.setIn(input.toReturn(QUIT).atSomePoint());

        String exitMessage = "Thank you for visiting biblioteca";

        new LibraryUI(mockedLibrary);

        assertThat(outContent.toString(), containsString(exitMessage));
    }

    @Test
    public void menuItem1ListsBooks() {
        System.setIn(input.toReturn(LIST_BOOKS).then(QUIT).atSomePoint());

        new LibraryUI(mockedLibrary);

        verify(mockedLibrary).list();
    }

    @Test
    public void menuItem1ListsBooksInColumnFormat() {
        System.setIn(input.toReturn(LIST_BOOKS).then(QUIT).atSomePoint());
        String listHeadings = "Author                          Title                          Year    ";

        new LibraryUI(mockedLibrary);

        assertThat(outContent.toString(), containsString(listHeadings));
    }

    @Test
    public void menuItem1GetsBooksDetails() {
        System.setIn(input.toReturn(LIST_BOOKS).then(QUIT).atSomePoint());
        List<Book> books = new ArrayList<Book>();
        books.add(mockedBook);
        when(mockedLibrary.list()).thenReturn(books);

        new LibraryUI(mockedLibrary);

        verify(mockedBook).getName();
        verify(mockedBook).getAuthor();
        verify(mockedBook).getYear();
    }


    @Test
    public void failureMessageOnIncorrectMenuSelection() throws Exception {
        System.setIn(input.toReturn("err").then(QUIT).atSomePoint());
        String menuError = "Sorry that is not a valid option";

        new LibraryUI(mockedLibrary);

        assertThat(outContent.toString(), containsString(menuError));
    }

    @Test
    public void menuItem2AllowsCustomersToCheckOutBook() {
        System.setIn(input.toReturn(CHECKOUT_BOOK).then(BOOK_NAME).then(QUIT).atSomePoint());

        new LibraryUI(mockedLibrary);
        String checkoutMsg = "Please enter the title of the book you would like to checkout";

        assertThat(outContent.toString(), containsString(checkoutMsg));
        verify(mockedLibrary).checkout(BOOK_NAME);
    }

    @Test
    public void failureMessageOnIncorrectBookSelection() {
        System.setIn(input.toReturn(CHECKOUT_BOOK).then(BOOK_NAME).then(QUIT).atSomePoint());
        when(mockedLibrary.checkout(BOOK_NAME)).thenReturn(false);

        new LibraryUI(mockedLibrary);
        String checkoutMsg = "That book is not available";

        assertThat(outContent.toString(), containsString(checkoutMsg));
    }

    @Test
    public void successMessageOnCheckout() {
        System.setIn(input.toReturn(CHECKOUT_BOOK).then(BOOK_NAME).then(QUIT).atSomePoint());
        when(mockedLibrary.checkout(BOOK_NAME)).thenReturn(true);

        new LibraryUI(mockedLibrary);

        String checkoutMessage = "Thank you! Enjoy the book";

        assertThat(outContent.toString(), containsString(checkoutMessage));
    }

    @Test
    public void menuItem3AllowsCustomersToReturnBook() {
        System.setIn(input.toReturn(RETURN_BOOK).then(BOOK_NAME).then(QUIT).atSomePoint());


        new LibraryUI(mockedLibrary);
        String returnMsg = "Please enter the title of the book you would like to return";

        assertThat(outContent.toString(), containsString(returnMsg));
        verify(mockedLibrary).returnBook(BOOK_NAME);

    }

    @Test
    public void successMessageOnReturnOfBook() {
        System.setIn(input.toReturn(RETURN_BOOK).then(BOOK_NAME).then(QUIT).atSomePoint());
        when(mockedLibrary.returnBook(BOOK_NAME)).thenReturn(true);

        new LibraryUI(mockedLibrary);
        String returnMsg = "Thank you for returning the book.";

        assertThat(outContent.toString(), containsString(returnMsg));
    }

    @Test
    public void failureMessageIfReturnedBookNotPreviouslyCheckedOut() {
        System.setIn(input.toReturn(RETURN_BOOK).then(BOOK_NAME).then(QUIT).atSomePoint());
        when(mockedLibrary.returnBook(BOOK_NAME)).thenReturn(false);

        new LibraryUI(mockedLibrary);
        String returnMsg = "That is not a valid book to return.";

        assertThat(outContent.toString(), containsString(returnMsg));
    }
}