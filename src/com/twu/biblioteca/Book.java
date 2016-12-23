package com.twu.biblioteca;

/**
 * Created by fergyo on 16/12/2016.
 */
public class Book extends Library_Item{

    private String name;
    private String author;
    private int year;
    private static final String COL_FORMAT = "%-30.30s  %-30.30s %-8.4s\n";
    public static final String HEADERS = String.format(COL_FORMAT, "Author", "Title", "Year");


    Book(String bookAuthor, String bookName, int publishingYear){
        author = bookAuthor;
        name = bookName;
        year = publishingYear;
    }

    @Override
    public String toString() {
        return String.format(COL_FORMAT,author,name,year);
    }

    String getName(){
        return name;
    }

    String getAuthor(){
        return author;
    }

    int getYear(){
        return year;
    }
}
