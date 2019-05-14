package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Scanner;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Storage storage = Storage.getInstance("www.yourShortUrl.com/");
        String homeDirectory = System.getProperty("user.home");
        FileOperation fileOperation = new FileOperation(new File(homeDirectory + "/URL.txt"), storage);
        Scanner scan = new Scanner(System.in);
        UserController userController = new UserController(log, scan);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    Thread.sleep(200);
                    fileOperation.fillFile();
                    log.warn("Your app is stopped");
                } catch (InterruptedException ex) {
                    log.warn("Your applications was interrupted");
                }
            }
        });

        userController.start();

    }
}

