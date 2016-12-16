package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by fergyo on 16/12/2016.
 */
public class LibraryTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }


    @Test
    public void welcomeMessageOnInit(){
        new Library();
        assertThat(outContent.toString(), CoreMatchers.containsString("Hello and welcome to the Biblioteca"));
    }

    @Test
    public void listBooksOnInit(){
        new Library();
        String bookList = "Purity - Jonathan Franzen - 2015";
        assertThat(outContent.toString(), CoreMatchers.containsString(bookList));
    }


}