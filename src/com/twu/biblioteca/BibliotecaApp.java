package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {

        LibraryUI libraryInterface = new LibraryUI(new Library());
        libraryInterface.start();

    }
}
