package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fergyo on 16/12/2016.
 */

public class LibraryUI {

    private static final String MENU = "What action would you like to perform today?\n" +
            "1. List books\n" +
            "2. Checkout book\n" +
            "3. Return book\n" +
            "Q. Quit program\n" +
            "Type the number of the desired menu item and press enter";

    private Library library;

    LibraryUI(Library library){
        this.library = library;
        startScript();
    }

    private void startScript() {
        boolean inLibrary = true;

        System.out.println("Hello and welcome to the Biblioteca");
        System.out.println("--------");

        while(inLibrary){
            inLibrary = displayMenu();
        }

        System.out.println("Thank you for visiting biblioteca");
    }

    private boolean displayMenu(){
        System.out.println(MENU);

        return selectMenu(InputHelper.getUserInput(""));
    }

     private boolean selectMenu(String input) {
         if (input.equals("Q")) return false;
         else if (input.equals("1")) System.out.println(listBooks());
         else if (input.equals("2")) System.out.println(checkOutBook());
         else if (input.equals("3")) System.out.println(returnBook());
         else System.out.println("Sorry that is not a valid option");

        return true;

    }

    private String listBooks() {
        return library.list();
    }

    private String checkOutBook() {
        return library.checkout(
                InputHelper.getUserInput("Please enter the title of the book you would like to checkout")
        );
    }

    private String returnBook() {
        return library.returnBook(
                InputHelper.getUserInput("Please enter the title of the book you would like to return")
        );
    }


}
