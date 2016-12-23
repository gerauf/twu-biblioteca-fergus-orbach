package com.twu.biblioteca;

/**
 * Created by fergyo on 22/12/2016.
 */
public class Movie extends LibraryItem {

    private final String name;
    private final String director;
    private final int year;
    private int rating;
    private static final String COL_FORMAT = "%-30.30s  %-30.30s %-10.8s %-8.4s\n";
    public static final String HEADERS = String.format(COL_FORMAT, "Title", "Director", "Rating", "Year");


    public int getYear() {
        return year;
    }

    public Movie(String name, String director, int rating, int year){
        this.name = name;
        this.director = director;
        this.rating = rating;
        this.year = year;

    }

    Movie(String name, String director, int year){
        this.name = name;
        this.director = director;
        this.year = year;
    }

    @Override
    public String toString() {
        String rating = this.rating == 0 ? "Unrated" : String.valueOf(this.rating);
        return String.format(COL_FORMAT, name, director, rating, year);
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }
}
