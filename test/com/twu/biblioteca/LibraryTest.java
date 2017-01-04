package com.twu.biblioteca;


import org.junit.Test;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * Created by fergyo on 16/12/2016.
 */
public class LibraryTest {

    private final Library library = new Library();
    private final String BOOK_NAME = "Purity";
    private final String BOOK_COL_FORMAT = "%-30.30s  %-30.30s %-8.4s\n";
    private final String MOVIE_COL_FORMAT = "%-30.30s  %-30.30s %-10.8s %-8.4s\n";
    private final String USER_COL_FORMAT = "%-30.30s  %-30.30s %-11.11s\n";
    private final String BOOK_DETAILS = String.format(BOOK_COL_FORMAT, "Jonathan Franzen", BOOK_NAME ,2015);
    private final String MOVIE_DETAILS = String.format(MOVIE_COL_FORMAT, "Terminator","James Cameron", 8, 1985);
    private final String USER_ID = "123-4567";
    private final String PASSWORD = "passw0rd";
    private final String USER_NAME = "Joe Bloggs";
    private final String USER_DETAILS = String.format(USER_COL_FORMAT, USER_NAME, "jb@gmail.com", "07969761562");

    @Test
    public void listReturnsBooksWhenSuppliedWithBookClass() {
        assertThat(library.listAvailableItemsOfType(Book.class), containsString(BOOK_DETAILS));
    }

    @Test
    public void listReturnsMoviesWhenSuppliedWithMovieClass() {
        assertThat(library.listAvailableItemsOfType(Movie.class), containsString(MOVIE_DETAILS));
    }

    @Test
    public void checkedOutItemsAreRemovedFromList() {
        library.checkoutItem(BOOK_NAME);
        assertThat(library.listAvailableItemsOfType(Book.class), not(containsString(BOOK_DETAILS)));
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

        assertThat(library.listAvailableItemsOfType(Book.class), containsString(BOOK_DETAILS));
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

    @Test
    public void checkPasswordReturnsTrueIfSuppliedWithAValidIDAndPassword() {
        assertTrue(library.checkPassword(USER_ID, PASSWORD));
    }

    @Test
    public void checkPasswordReturnsFalseIfIncorrectPassword() {
        assertFalse(library.checkPassword(USER_ID, "Wrong password"));
    }

    @Test
    public void initiallyNoActiveUser() {
        assertEquals(library.getActiveUserName(), "No User");
    }

    @Test
    public void whenUserHasCheckedInThenGetActiveUserNameReturnsName() {
        library.checkPassword(USER_ID, PASSWORD);
        assertEquals(USER_NAME, library.getActiveUserName());

    }

    @Test
    public void whenUserHasCheckedInActiveUserDetailsReturnsUserString() {
        library.checkPassword(USER_ID, PASSWORD);
        assertEquals(USER_DETAILS, library.activeUserDetails());
    }

    @Test
    public void whenUserHasNotCheckedInActiveUserDetailsReturnsNoUser() {
        assertEquals("No User", library.activeUserDetails());
    }
}