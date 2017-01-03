package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by fergyo on 13/12/2016.
 */
public class InputHelper {
    public static String getUserInput(String prompt){

        String inputLine = null;

        System.out.println(prompt + " ");
        try{
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if(inputLine.length() == 0) return null;
        }catch(IOException e){
            System.out.println("IOException: " + e);
        }

        return inputLine;
    }
}
