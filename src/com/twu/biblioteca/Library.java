package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fergyo on 16/12/2016.
 */
public class Library {

    private List<LibraryItem> items = new ArrayList<>();

    Library(){
        fillLibrary();
    }

    String listAvailableItemsOfType(Class itemType){
        String list = "";
        for(LibraryItem item: items){
            if (isCorrectTypeAndAvailable(item, itemType)) list += item.toString();
        }
        return list;
    }

    private boolean isCorrectTypeAndAvailable(LibraryItem item, Class itemType) {
        return item.isCheckedIn() && item.getClass() == itemType;
    }

    boolean checkoutItem(String itemName) {
        for(LibraryItem item: items){
            if(item.getName().equals(itemName) && item.isCheckedIn()){
                item.checkoutItem();
                return true;
            }
        }
        return false;
    }

    boolean returnItem(String itemName) {
        for(LibraryItem item: items){
            if(item.getName().equals(itemName) && !item.isCheckedIn()){
                item.returnItem();
                return true;
            }
        }
        return false;
    }

    private void fillLibrary() {
        items.add(new Book("Jonathan Franzen", "Purity", 2015));
        items.add(new Book("Dave Edgars", "The Circle", 2015));
        items.add(new Book("Henri Charrière", "Papillon", 1969));
        items.add(new Movie("Terminator", "James Cameron", 8, 1985));
        items.add(new Movie("King Arthur", "Guy Ritchie", 2017));
    }
}
