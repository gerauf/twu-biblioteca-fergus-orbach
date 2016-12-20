package com.twu.biblioteca;

import com.sun.javafx.binding.StringFormatter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fergyo on 16/12/2016.
 */
public class Library {

    private List<Book> books = new ArrayList<Book>();

    Library(){
        fillLibrary();
    }

    String list(){
        String columnFormat = "%-30.30s  %-30.30s %-8.4s\n";
        String list = String.format(columnFormat,"Author", "Title", "Year");

        for(Book book: books){
            if (book.isCheckedIn()){
                list += String.format(columnFormat,book.getAuthor(),book.getName(),book.getYear());
            }
        }

        return list;
    }

    String checkout(String bookName) {
        for(Book book: books){
            if(book.getName().equals(bookName)){
                book.checkOut();
                return "Thank you! Enjoy the book";
            }
        }
        return "That book is not available";
    }

    String returnBook(String bookName) {
        for(Book book: books){
            if(book.getName().equals(bookName) && !book.isCheckedIn()){
                book.returnBook();
                return "Thank you for returning the book.";
            }
        }
        return "That is not a valid book to return.";
    }

    private void fillLibrary() {
        books.add(new Book("Jonathan Franzen", "Purity", 2015));
        books.add(new Book("Dave Edgars", "The Circle", 2015));
        books.add(new Book("Henri Charrière", "Papillon", 1969));
    }
}
