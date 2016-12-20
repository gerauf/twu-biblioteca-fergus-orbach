package com.twu.biblioteca;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by fergyo on 16/12/2016.
 */
public class LibraryTest {

    private Library lib = new Library();
    private final String bookName = "Purity";

    @Test
    public void listsReturnsArrayOfBooksStartingWithABookNamedPurity() {
        Book book1 = lib.list().get(0);
        assertEquals(book1.getName(), bookName);
    }

    @Test
    public void libraryInitialisesWithThreeBooks() {
        assertEquals(lib.list().size(), 3);
    }

    @Test
    public void checkedOutBooksAreRemovedFromList() {
        lib.checkout(bookName);

        for(Book book: lib.list()) assertNotEquals(book.getName(), bookName);
    }

    @Test
    public void checkOutReturnsTrueOnSuccess() {
        assertTrue(lib.checkout(bookName));
    }

    @Test
    public void checkOutReturnsFalseIfBookNameNotPresent() {
        assertFalse(lib.checkout("Puerility"));
    }

    @Test
    public void checkOutReturnsFalseIfBookAlreadyCheckedOut() {
        lib.checkout(bookName);
        assertFalse(lib.checkout(bookName));
    }

    @Test
    public void returnedBooksShowUpInLibrary() {
        String bookNames = "";

        lib.checkout(bookName);
        lib.returnBook(bookName);

        for(Book book: lib.list()) bookNames += book.getName();

        assertThat(bookNames, containsString(bookName));
    }

    @Test
    public void returnBookReturnsTrueOnSuccess() {
        lib.checkout(bookName);

        assertTrue(lib.returnBook(bookName));
    }

    @Test
    public void returnBookReturnsFalseIfBookNotCheckedOutFirst() {
        assertFalse(lib.returnBook(bookName));
    }

    @Test
    public void returnBookReturnsFalseIfNotInLibrary() {
        lib.checkout(bookName);

        assertFalse(lib.returnBook("Puerility"));
    }
}