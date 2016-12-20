package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by fergyo on 16/12/2016.
 */
public class LibraryTest {

    private Library lib = new Library();
    private final String bookName = "Purity";
    private final String bookAuthor = "Jonathan Franzen";
    private final int bookYear = 2015;

    @Test
    public void listsBooksInColumnFormat() throws Exception {
        String results = "Author                          Title                          Year    \n";
        assertThat(lib.list(), containsString(results));

    }

    @Test
    public void libraryInitsWithBooks() throws Exception {
        assertThat(lib.list(), CoreMatchers.allOf(
                containsString(Integer.toString(bookYear)),
                containsString(bookName),
                containsString(bookAuthor)
        ));
    }

    @Test
    public void checkedOutBooksAreRemovedFromList() throws Exception {
        String bookName = "Purity";
        lib.checkout("Purity");

        assertThat(lib.list(), CoreMatchers.not(containsString(bookName)));
    }

    @Test
    public void successfulCheckOutReturnsMessage() throws Exception {
        String checkOutMessage = "Thank you! Enjoy the book";

        assertThat(lib.checkout(bookName), containsString(checkOutMessage));
    }

    @Test
    public void unsuccessfulCheckOutReturnsMessage() throws Exception {
        String bookName = "Puberty";
        String successMessage = "Thank you! Enjoy the book";
        String failureMessage = "That book is not available";

        assertThat(lib.checkout(bookName), CoreMatchers.not(containsString(successMessage)));
        assertThat(lib.checkout(bookName), containsString(failureMessage));
    }

    @Test
    public void returnedBooksShowUpInLibrary() throws Exception {
        lib.checkout(bookName);
        lib.returnBook(bookName);

        assertThat(lib.list(), CoreMatchers.allOf(
                containsString(Integer.toString(bookYear)),
                containsString(bookName),
                containsString(bookAuthor)
        ));
    }

    @Test
    public void successMessageOnReturnOfBook() throws Exception {
        lib.checkout(bookName);
        String successMessage = "Thank you for returning the book.";

        assertThat(lib.returnBook(bookName), containsString(successMessage));
    }

    @Test
    public void booksNeedToBeCheckedOutToReturn() throws Exception {
        String failureMessage = "That is not a valid book to return.";

        assertThat(lib.returnBook(bookName), containsString(failureMessage));

    }

    @Test
    public void booksNeedToHaveComeFromLibraryToReturn() throws Exception {
        lib.checkout(bookName);
        String failureMessage = "That is not a valid book to return.";

        assertThat(lib.returnBook("Puerility"), containsString(failureMessage));
    }
}