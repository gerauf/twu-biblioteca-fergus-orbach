package com.twu.biblioteca;

/**
 * Created by fergyo on 16/12/2016.
 */
public class Book extends LibraryItem {

    private String name;
    private String author;
    private int year;
    private static final String COL_FORMAT = "%-30.30s  %-30.30s %-8.4s\n";

    Book(String bookAuthor, String bookName, int publishingYear){
        author = bookAuthor;
        name = bookName;
        year = publishingYear;
    }

    @Override
    public String toString() {
        return String.format(COL_FORMAT,author,name,year);
    }

    @Override
    String getName(){
        return name;
    }
}
