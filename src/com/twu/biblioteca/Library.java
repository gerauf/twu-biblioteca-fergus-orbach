package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fergyo on 16/12/2016.
 */
public class Library {

    private List<Book> books = new ArrayList<Book>();

    Library(){
        startScript();
    }

    private void startScript() {
        fillLibrary();
        System.out.println("Hello and welcome to the Biblioteca");
        System.out.println("--------");
        printLibrary();
    }

    private void printLibrary() {
        String columnFormat = "%-30.30s  %-30.30s %-8.4s\n";

        System.out.printf(columnFormat, "Author", "Title", "Year");

        for(Book book:books){
            System.out.printf(columnFormat, book.getAuthor(), book.getName(), book.getYear());
        }
    }

    private void fillLibrary() {
        books.add(new Book("Jonathan Franzen", "Purity", 2015));
        books.add(new Book("Dave Edgars", "The Circle", 2015));
        books.add(new Book("Henri Charrière", "Papillon", 1969));
    }

}
