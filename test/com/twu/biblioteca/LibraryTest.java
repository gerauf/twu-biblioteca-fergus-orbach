package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fergyo on 16/12/2016.
 */
public class LibraryTest {

    Library lib = new Library();

    @Test
    public void listsBooksInColumnFormat() throws Exception {
        String results = "Author                          Title                          Year    \n";
        assertThat(lib.list(), CoreMatchers.containsString(results));

    }

    @Test
    public void libraryInitsWithBooks() throws Exception {
        String bookName = "Purity";
        String bookAuthor = "Jonathan Franzen";
        int bookYear = 2015;

        assertThat(lib.list(), CoreMatchers.containsString(bookName));
        assertThat(lib.list(), CoreMatchers.containsString(bookAuthor));
        assertThat(lib.list(), CoreMatchers.containsString(Integer.toString(bookYear)));
    }
}