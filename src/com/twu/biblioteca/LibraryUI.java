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
            "5. Login\n" +
            "Q. Quit program\n" +
            "Type the number of the desired menu item and press enter";

    private Library library;

    LibraryUI(Library library){
        this.library = library;
    }

    void start() {
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
        switch (input) {
            case "Q": return false;
            case "1": System.out.println(library.listAvailableItemsOfType(Book.class));
                break;
            case "2": System.out.println(library.listAvailableItemsOfType(Movie.class));
                break;
            case "3": System.out.println(checkOutItem());
                break;
            case "4": System.out.println(returnItem());
                break;
            case "5": System.out.println(login());
                break;
            default: System.out.println("Sorry that is not a valid option");
                break;
        }
        return true;
    }

    private String checkOutItem() {
        if(noActiveUser()) return "A user must be logged in to checkout an item";

        String itemName = InputHelper.getUserInput("Please enter the title of the item you would like to checkoutItem");

        return library.checkoutItem(itemName) ? "Thank you! Enjoy your selection" : "That selection is not available";
    }

    private String returnItem() {
        if(noActiveUser()) return "A user must be logged in to return an item";

        String itemName = InputHelper.getUserInput("Please enter the title of the item you would like to return");

        return library.returnItem(itemName) ? "Thank you for returning the item" : "That is not a valid item to return";
    }

    private String login() {
        String userID = InputHelper.getUserInput("Please enter your library number");
        String password = InputHelper.getUserInput("Please enter your password");

        if(library.checkPassword(userID, password)) return activeUserName() + " is now logged in";
        return "Password or user ID is incorrect";
    }

    private String activeUserName() {
        return library.getActiveUserName();
    }

    private boolean noActiveUser() {
        return activeUserName().equals("No User");
    }
}
