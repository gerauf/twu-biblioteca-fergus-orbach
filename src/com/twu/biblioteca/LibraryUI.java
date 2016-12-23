package com.twu.biblioteca;

/**
 * Created by fergyo on 16/12/2016.
 */

public class LibraryUI {

    private static final String MENU = "What action would you like to perform today?\n" +
            "1. List books\n" +
            "2. List movies\n" +
            "3. Checkout item\n" +
            "4. Return item\n" +
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
         else if (input.equals("1")) System.out.println(library.list(Book.class));
         else if (input.equals("2")) System.out.println(library.list(Movie.class));
         else if (input.equals("3")) System.out.println(checkOutItem());
         else if (input.equals("4")) System.out.println(returnItem());
         else System.out.println("Sorry that is not a valid option");

        return true;

    }

    private String checkOutItem() {
        String itemName = InputHelper.getUserInput("Please enter the title of the item you would like to checkoutItem");
        if (library.checkoutItem(itemName)){
            return "Thank you! Enjoy your selection";
        }
        return "That selection is not available";
    }

    private String returnItem() {
        String itemName = InputHelper.getUserInput("Please enter the title of the item you would like to return");
        if (library.returnItem(itemName)){
            return "Thank you for returning the item.";
        }
        return "That is not a valid item to return.";
    }


}
