package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fergyo on 16/12/2016.
 */

public class LibraryUI {

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
        System.out.println("What action would you like to perform today?");
        System.out.println("1. List books");
        System.out.println("Q. Quit program");
        System.out.println("Type the number of the desired menu item and press enter");

        return selectMenu(InputHelper.getUserInput(""));

    }

     private boolean selectMenu(String input) {
         if (input.equals("Q")) return false;
         else if (input.equals("1")) System.out.println(library.list());
         else System.out.println("Sorry that is not a valid option");

        return true;

    }


}
