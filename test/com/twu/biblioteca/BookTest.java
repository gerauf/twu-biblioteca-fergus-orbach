package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fergyo on 16/12/2016.
 */
public class BookTest {


    String bookName = "The Circle";
    String bookAuthor = "Dave Edgars";
    int bookYear = 2014;

    Book book1 = new Book(bookAuthor, bookName, bookYear);


    @Test
    public void booksHaveNames() throws Exception {
        assertEquals(bookName, book1.getName());
    }

    @Test
    public void booksHaveAuthors() throws Exception {
        assertEquals(bookAuthor, book1.getAuthor());
    }

    @Test
    public void booksHavePublishedYear() throws Exception {
        assertEquals(bookYear, book1.getYear());
    }

    @Test
    public void booksInitialisedAsCheckedIn() throws Exception {
        assertTrue(book1.isCheckedIn());
    }

    @Test
    public void booksCanBeCheckedOut() throws Exception {
        book1.checkOut();
        assertFalse(book1.isCheckedIn());
    }

    @Test
    public void booksCanBeReturned() throws Exception {
        book1.checkOut();
        book1.returnBook();
        assertTrue(book1.isCheckedIn());
    }
}