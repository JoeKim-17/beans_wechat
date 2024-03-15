package com.levelup;

import java.util.Scanner;
import com.google.gson.*;
public class Client {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            new Handler(scanner).start();
        } catch (SecurityException e) {
            e.printStackTrace();
        }  
    }

}
