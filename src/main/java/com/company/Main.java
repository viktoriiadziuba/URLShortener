package com.company;

import org.apache.log4j.PropertyConfigurator;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args)  {
        PropertyConfigurator.configure("src/log4j.properties");

        Shortener shortUrl = new Shortener();
        URLDecoder longUrl = new URLDecoder();
        Scanner sc = new Scanner(System.in);
        String b = "";

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    Thread.sleep(200);
                    shortUrl.fillFile();
                    log.warning("Your app is stopped");
                   // System.out.println(" " + "Your app is stopped");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        while (true) {

            log.log(Level.INFO, "Please choose what you would like to do with your URL:");
            log.log(Level.INFO, "In case if you want to make your URL shorter, please enter 1");
            log.log(Level.INFO, "In case if you want to get your original URL by its short version, please enter 2");
            log.log(Level.INFO, "If you would like to stop the application, please press CTRL + C");
            log.log(Level.INFO,"Please enter your choice:");

            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        log.log(Level.INFO,"Enter your long URL:");
                        String lUrl = sc.next();
                        if (URLValidator.urlValidator(lUrl)) {
                            log.log(Level.INFO,"This is your short URL:");
                            shortUrl.encode(lUrl);
                        } else {
                            log.log(Level.INFO,"This URL is not correct");
                        }
                        break;

                    case 2:
                        log.log(Level.INFO,"Enter you short URL");
                        String sUrl = sc.next();
                        log.log(Level.INFO,"This is your long URL:");
                        longUrl.decode(sUrl);
                        break;

                    default:
                        log.log(Level.INFO,"Please enter one number from 1 to 3");

                }


            } catch (InputMismatchException e) {
                log.warning("Please enter the number of option from the list above");
                break;
            }
        }


    }


}

