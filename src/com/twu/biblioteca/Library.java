package com.twu.biblioteca;


import java.util.ArrayList;
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
        String list = Book.HEADERS;
        for(Book book: books){
            if (book.isCheckedIn()) list += book.toString();
        }
        return list;
    }

    boolean checkout(String bookName) {
        for(Book book: books){
            if(book.getName().equals(bookName) && book.isCheckedIn()){
                book.checkOut();
                return true;
            }
        }
        return false;
    }

    boolean returnBook(String bookName) {
        for(Book book: books){
            if(book.getName().equals(bookName) && !book.isCheckedIn()){
                book.returnBook();
                return true;
            }
        }
        return false;
    }

    private void fillLibrary() {
        books.add(new Book("Jonathan Franzen", "Purity", 2015));
        books.add(new Book("Dave Edgars", "The Circle", 2015));
        books.add(new Book("Henri Charrière", "Papillon", 1969));
    }
}
