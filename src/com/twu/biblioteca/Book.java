package com.twu.biblioteca;

/**
 * Created by fergyo on 16/12/2016.
 */
public class Book {

    private String name;
    private String author;
    private int year;

    Book(String bookName, String bookAuthor, int publishingYear){
        name = bookName;
        author = bookAuthor;
        year = publishingYear;
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
