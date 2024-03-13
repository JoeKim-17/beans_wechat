package com.levelup;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Client {
    private static final Logger logger = Logger.getLogger(Client.class.getName());
    private static SimpleFormatter formatter = new SimpleFormatter();

    public static void main(String[] args) {
        FileHandler fh;
        try {
            fh = new FileHandler("./MyLogFile.log");
            logger.addHandler(fh);
            fh.setFormatter(formatter);
            Scanner scanner = new Scanner(System.in);
            new Handler(scanner, logger).start();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
