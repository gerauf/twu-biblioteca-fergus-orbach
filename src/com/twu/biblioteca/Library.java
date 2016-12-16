package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fergyo on 16/12/2016.
 */
public class Library {

    private List<String> books = new ArrayList<String>();

    Library(){
        startScript();
    }

    private void startScript() {
        fillLibrary();
        System.out.println("Hello and welcome to the Biblioteca");
        System.out.println("--------");

        for(String book:books) System.out.println(book);
    }

    private void fillLibrary() {
        books.add("Purity - Jonathan Franzen");
        books.add("The Circle - Dave Edgars");
        books.add("Space - Stephen Baxter");
    }

}
