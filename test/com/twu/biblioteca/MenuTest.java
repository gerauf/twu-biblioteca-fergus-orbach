package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fergyo on 24/12/2016.
 */
public class MenuTest {

    private Menu menu = new Menu();

    @Test
    public void menuStartsWithAQuitOption() {
        assertEquals("0. Quit programme\n", menu.list());
    }

    @Test
    public void itemsAddedToMenuAreIndexedByNumber() {
        menu.add("List books", "list");
        assertEquals("0. Quit programme\n1. List books\n", menu.list());
    }

    @Test
    public void itemsCanBeRemovedBasedOnIndex() {
        menu.add("List books", "list");
        menu.add("Something else", "doSomething");
        menu.remove(1);
        assertEquals("0. Quit programme\n1. Something else\n", menu.list());
    }

    @Test
    public void selectingAnItemReturnsItsAssociatedMethodName() {
        menu.add("List books", "list");
        assertEquals("list", menu.select(1));
    }
}