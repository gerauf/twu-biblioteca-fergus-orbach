package com.twu.biblioteca;

/**
 * Created by fergyo on 16/12/2016.
 */
public class Book {

    private String name;
    private String author;
    private int year;

    Book(String bookAuthor, String bookName, int publishingYear){
        author = bookAuthor;
        name = bookName;
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
