package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.*;

/**
 * Created by fergyo on 16/12/2016.
 */
public class LibraryTest {

    private Library lib = new Library();
    private final String bookName = "Purity";

    @Test
    public void listsReturnsArrayOfBooks() throws Exception {
        assertThat(lib.list().get(0), isA((Book.class)));

    }

    @Test
    public void libraryInitialisesWithThreeBooks() throws Exception {
        assertEquals(lib.list().size(), 3);
    }

    @Test
    public void checkedOutBooksAreRemovedFromList() throws Exception {
        lib.checkout(bookName);

        List<Book> books = lib.list();
        for(Book book: books){
            assertNotEquals(book.getName(), bookName);
        }

    }

    @Test
    public void checkOutReturnsTrueOnSuccess() throws Exception {
        assertTrue(lib.checkout(bookName));
    }

    @Test
    public void checkOutReturnsFalseIfBookNameNotPresent() throws Exception {
        assertFalse(lib.checkout("Puerility"));
    }

    @Test
    public void checkOutReturnsFalseIfBookAlreadyCheckedOut() throws Exception {
        lib.checkout(bookName);
        assertFalse(lib.checkout(bookName));
    }

    @Test
    public void returnedBooksShowUpInLibrary() throws Exception {
        lib.checkout(bookName);
        lib.returnBook(bookName);

        List<Book> books = lib.list();
        String bookNames = "";

        for(Book book: books){
            bookNames += book.getName();
        }

        assertThat(bookNames, containsString(bookName));
    }

    @Test
    public void returnBookReturnsTrueOnSuccess() throws Exception {
        lib.checkout(bookName);

        assertTrue(lib.returnBook(bookName));
    }

    @Test
    public void returnBookReturnsFalseIfBookNotCheckedOutFirst() throws Exception {
        assertFalse(lib.returnBook(bookName));
    }

    @Test
    public void returnBookReturnsFalseIfNotInLibrary() throws Exception {
        lib.checkout(bookName);

        assertFalse(lib.returnBook("Puerility"));
    }
}