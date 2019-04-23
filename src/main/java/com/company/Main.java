package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        Shortener shortUrl = new Shortener();
        URLDecoder longUrl = new URLDecoder();
        Scanner sc = new Scanner(System.in);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    Thread.sleep(200);
                    shortUrl.fillFile();
                    log.warn("Your app is stopped");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        while (true) {

            log.info("Please choose what you would like to do with your URL:");
            log.info("In case if you want to make your URL shorter, please enter 1");
            log.info("In case if you want to get your original URL by its short version, please enter 2");
            log.info("If you would like to stop the application, please press CTRL + C");
            log.info("Please enter your choice:");

            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        log.info("Enter your long URL:");
                        String lUrl = sc.next();
                        if (URLValidator.urlValidator(lUrl)) {
                            log.info("This is your short URL:");
                            shortUrl.encode(lUrl);
                            shortUrl.fillMap(lUrl);
                        } else {
                            log.info("This URL is not correct");
                        }
                        break;

                    case 2:
                        log.info("Enter you short URL");
                        String sUrl = sc.next();
                        log.info("This is your long URL:");
                        longUrl.decode(sUrl);
                        break;

                    default:
                        log.info("Please enter one number from 1 to 3");

                }


            } catch (InputMismatchException e) {
                log.warn("Please enter the number of option from the list above");
                break;
            }
        }


    }


}

