package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fergyo on 16/12/2016.
 */
public class BookTest {


    private final String NAME = "The Circle";
    private final String AUTHOR = "Dave Edgars";
    private final int YEAR = 2014;
    private final String COL_FORMAT = "%-30.30s  %-30.30s %-8.4s\n";

    private Book book1 = new Book(AUTHOR, NAME, YEAR);


    @Test
    public void booksHaveNames() throws Exception {
        assertEquals(NAME, book1.getName());
    }

    @Test
    public void booksInitialisedAsCheckedIn() throws Exception {
        assertTrue(book1.isCheckedIn());
    }

    @Test
    public void booksCanBeCheckedOut() throws Exception {
        book1.checkoutItem();
        assertFalse(book1.isCheckedIn());
    }

    @Test
    public void booksCanBeReturned() throws Exception {
        book1.checkoutItem();
        book1.returnItem();
        assertTrue(book1.isCheckedIn());
    }

    @Test
    public void toStringPrintsBooksDetailsInColumnFormat() {
        String bookDetails =String.format(COL_FORMAT,AUTHOR, NAME, YEAR);
        assertEquals(bookDetails, book1.toString());
    }

}