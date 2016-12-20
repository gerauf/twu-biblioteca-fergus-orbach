package com.twu.biblioteca;

/**
 * Created by fergyo on 16/12/2016.
 */
public class Book {

    private String name;
    private String author;
    private int year;
    private boolean checkedIn = true;

    Book(String bookAuthor, String bookName, int publishingYear){
        author = bookAuthor;
        name = bookName;
        year = publishingYear;
    }

    void checkOut() {
        checkedIn = false;
    }

    void returnBook() {
        checkedIn = true;
    }

    boolean isCheckedIn() {
        return checkedIn;
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
