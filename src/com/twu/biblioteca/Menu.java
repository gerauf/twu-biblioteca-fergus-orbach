package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fergyo on 24/12/2016.
 */
public class Menu {

    private List<String[] > menu = new ArrayList<String[]>();

    Menu(){
        String[] quit = {"Quit programme", "quit"};
        menu.add(quit);
    }

    public String list() {
        String list = "";
        for(String[] item: menu){
            list += menu.indexOf(item) + ". " + item[0] + "\n";
        }
        return list;
    }

    public void add(String menuItem, String method) {
        String[] input = {menuItem, method};
        menu.add(input);
    }

    public void remove(int index) {
        menu.remove(index);
    }

    public String select(int index) {
        return menu.get(index)[1];
    }
}
