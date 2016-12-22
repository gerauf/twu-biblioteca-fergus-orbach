package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fergyo on 22/12/2016.
 */
public class MovieTest {

    private final int RATING = 10;
    private final String DIRECTOR = "Frank Darabont";
    private final int YEAR = 1995;
    private final String NAME = "The Shawshank Redemption";
    private final Movie movie = new Movie(NAME, DIRECTOR, YEAR, RATING);

    @Test
    public void moviesHaveNames() {
        assertEquals(movie.getName(), NAME);
    }

    @Test
    public void moviesHaveADirector() {
        assertEquals(movie.getDirector(), DIRECTOR);
    }

    @Test
    public void moviesHaveAYear() {
        assertEquals(movie.getYear(), YEAR);
    }

    @Test
    public void moviesHaveaRating() {
        assertEquals(movie.getRating(), RATING);
    }
}