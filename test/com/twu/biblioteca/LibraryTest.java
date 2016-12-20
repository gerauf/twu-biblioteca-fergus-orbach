package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by fergyo on 16/12/2016.
 */
public class LibraryTest {

    Library lib = new Library();

    @Test
    public void listsBooksInColumnFormat() throws Exception {
        String results = "Author                          Title                          Year    \n";
        assertThat(lib.list(), containsString(results));

    }

    @Test
    public void libraryInitsWithBooks() throws Exception {
        String bookName = "Purity";
        String bookAuthor = "Jonathan Franzen";
        int bookYear = 2015;

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
        String bookName = "Purity";
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
}