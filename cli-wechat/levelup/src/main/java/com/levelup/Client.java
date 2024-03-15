package com.levelup;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            new Handler(scanner).start();
            // new ReceiverHandler().start();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

}
