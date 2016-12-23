package com.twu.biblioteca;


import org.junit.Test;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * Created by fergyo on 16/12/2016.
 */
public class LibraryTest {

    private Library library = new Library();
    private final String BOOK_NAME = "Purity";
    private String COL_FORMAT = "%-30.30s  %-30.30s %-8.4s";
    private String BOOK_DETAILS = String.format(COL_FORMAT, "Jonathan Franzen",BOOK_NAME,2015);

    @Test
    public void listsReturnsStringOfTitleAndBookDetails() {
        String HEADERS = String.format(COL_FORMAT, "Author", "Title", "Year");
        assertThat(library.list(), allOf(containsString(HEADERS), containsString(BOOK_DETAILS)));
    }

    @Test
    public void checkedOutBooksAreRemovedFromList() {
        library.checkout(BOOK_NAME);
        assertThat(library.list(), not(containsString(BOOK_DETAILS)));
    }

    @Test
    public void checkOutReturnsTrueOnSuccess() {
        assertTrue(library.checkout(BOOK_NAME));
    }

    @Test
    public void checkOutReturnsFalseIfBookNameNotPresent() {
        assertFalse(library.checkout("Puerility"));
    }

    @Test
    public void checkOutReturnsFalseIfBookAlreadyCheckedOut() {
        library.checkout(BOOK_NAME);
        assertFalse(library.checkout(BOOK_NAME));
    }

    @Test
    public void returnedBooksShowUpInLibrary() {
        library.checkout(BOOK_NAME);
        library.returnBook(BOOK_NAME);

        assertThat(library.list(), containsString(BOOK_DETAILS));
    }

    @Test
    public void returnBookReturnsTrueOnSuccess() {
        library.checkout(BOOK_NAME);

        assertTrue(library.returnBook(BOOK_NAME));
    }

    @Test
    public void returnBookReturnsFalseIfBookNotCheckedOutFirst() {
        assertFalse(library.returnBook(BOOK_NAME));
    }

    @Test
    public void returnBookReturnsFalseIfNotInLibrary() {
        assertFalse(library.returnBook("Puerility"));
    }
}