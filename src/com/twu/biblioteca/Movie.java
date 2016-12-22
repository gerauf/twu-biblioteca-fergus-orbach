package com.twu.biblioteca;

/**
 * Created by fergyo on 22/12/2016.
 */
public class Movie {

    private final String name;
    private final String director;
    private final int year;
    private int rating;

    public int getYear() {
        return year;
    }

    public Movie(String name, String director, int year, int rating){
        this.name = name;
        this.director = director;
        this.year = year;
        this.rating = rating;

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
