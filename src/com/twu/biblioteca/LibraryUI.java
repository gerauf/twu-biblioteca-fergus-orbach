package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fergyo on 16/12/2016.
 */

public class LibraryUI {

    private List<Book> books = new ArrayList<Book>();

    LibraryUI(){
        startScript();
    }

    private void startScript() {
        System.out.println("Hello and welcome to the Biblioteca");
        System.out.println("--------");
        displayMenu();
    }

    private void displayMenu(){
        System.out.println("What action would you like to perform today?");
        System.out.println("1. List books");
        System.out.println("Type the number of the desired menu item and press enter");
    }


}
