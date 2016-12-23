package com.twu.biblioteca;

import com.sun.javafx.binding.StringFormatter;
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
    private final String COL_FORMAT = "%-30.30s  %-30.30s %-10.8s %-8.4s\n";

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
    public void moviesHaveARating() {
        assertEquals(movie.getRating(), RATING);
    }

    @Test
    public void unratedMoviesHaveA0Rating() {
        Movie unratedMovie = new Movie(NAME, DIRECTOR, YEAR);
        assertEquals(unratedMovie.getRating(), 0);
    }

    @Test
    public void moviesInitialisedAsCheckedIn() throws Exception {
        assertTrue(movie.isCheckedIn());
    }

    @Test
    public void moviesCanBeCheckedOut() throws Exception {
        movie.checkOut();
        assertFalse(movie.isCheckedIn());
    }

    @Test
    public void moviesCanBeReturned() throws Exception {
        movie.checkOut();
        movie.returnBook();
        assertTrue(movie.isCheckedIn());
    }

    @Test
    public void toStringPrintsMoviesDetailsInColumnFormat() {
        String movieDetails =String.format(COL_FORMAT, NAME, DIRECTOR, RATING, YEAR);
        assertEquals(movieDetails, movie.toString());
    }

    @Test
    public void unratedMoviesPrintUnratedInRatingColumn() {
        String movieDetails =String.format(COL_FORMAT, NAME, DIRECTOR, RATING, YEAR);
        assertEquals(movieDetails, movie.toString());
    }

    @Test
    public void containsStaticConstantWhichDescribeColumnHeaders() {
        String columnHeaders = String.format(COL_FORMAT,"Title", "Director", "Rating", "Year");
        assertEquals(Movie.HEADERS, columnHeaders);
    }
}