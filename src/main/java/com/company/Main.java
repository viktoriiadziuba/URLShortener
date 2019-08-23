package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Storage storage = Storage.getInstance("www.yourShortUrl.com/");
        DBOperation DBOperation = new DBOperation(storage);
        Scanner scan = new Scanner(System.in);
        UserController userController = new UserController(log, scan);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    Thread.sleep(200);
                    DBOperation.fillDB();
                } catch (InterruptedException ex) {
                    log.warn("Your applications was interrupted");
                }
            }
        });

        userController.start();
    }
}

