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
    private String BOOK_COL_FORMAT = "%-30.30s  %-30.30s %-8.4s\n";
    private String MOVIE_COL_FORMAT = "%-30.30s  %-30.30s %-10.8s %-8.4s\n";;
    private String BOOK_DETAILS = String.format(BOOK_COL_FORMAT, "Jonathan Franzen",BOOK_NAME,2015);
    private String MOVIE_DETAILS = String.format(MOVIE_COL_FORMAT, "Terminator","James Cameron", 8, 1985);

    @Test
    public void listReturnsBooksWhenSuppliedWithBookClass() {
        assertThat(library.list(Book.class), containsString(BOOK_DETAILS));
        assertThat(library.list(Book.class), not(containsString(MOVIE_COL_FORMAT)));
    }

    @Test
    public void listReturnsMoviesWhenSuppliedWithMovieClass() {
        assertThat(library.list(Movie.class), containsString(MOVIE_DETAILS));
        assertThat(library.list(Movie.class), not(containsString(BOOK_DETAILS)));
    }

    @Test
    public void checkedOutItemsAreRemovedFromList() {
        library.checkoutItem(BOOK_NAME);
        assertThat(library.list(Book.class), not(containsString(BOOK_DETAILS)));
    }

    @Test
    public void checkOutItemReturnsTrueOnSuccess() {
        assertTrue(library.checkoutItem(BOOK_NAME));
    }

    @Test
    public void checkOutItemReturnsFalseIfBookNameNotPresent() {
        assertFalse(library.checkoutItem("Puerility"));
    }

    @Test
    public void checkOutItemReturnsFalseIfBookAlreadyCheckedOut() {
        library.checkoutItem(BOOK_NAME);
        assertFalse(library.checkoutItem(BOOK_NAME));
    }

    @Test
    public void returnedItemsShowUpInLibrary() {
        library.checkoutItem(BOOK_NAME);
        library.returnItem(BOOK_NAME);

        assertThat(library.list(Book.class), containsString(BOOK_DETAILS));
    }

    @Test
    public void returnItemReturnsTrueOnSuccess() {
        library.checkoutItem(BOOK_NAME);

        assertTrue(library.returnItem(BOOK_NAME));
    }

    @Test
    public void returnItemReturnsFalseIfItemNotCheckedOutFirst() {
        assertFalse(library.returnItem(BOOK_NAME));
    }

    @Test
    public void returnItemReturnsFalseIfNotInLibrary() {
        assertFalse(library.returnItem("Puerility"));
    }
}