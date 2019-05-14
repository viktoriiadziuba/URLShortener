package com.company;

import java.io.File;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.company.Main.log;

public class UserController {

    Storage storage = Storage.getInstance("www.yourShortUrl.com/");
    URLModification urlModification = new URLModification();
    String homeDirectory = System.getProperty("user.home");
    Scanner sc = new Scanner(System.in);
    FileOperation fileOperation = new FileOperation(new File(homeDirectory + "/URL.txt"), storage);
    HashMap<String, String> mapUrls = fileOperation.readFromFile();

    public void start() {
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
                            if (mapUrls.containsValue(lUrl)) {
                                StringBuilder sb = new StringBuilder("www.yourShortUrl.com/");
                                log.info(sb.append(urlModification.encode(lUrl)).toString());
                            } else {
                                urlModification.encode(lUrl);
                                storage.fillMap(lUrl);
                                StringBuilder sb = new StringBuilder("www.yourShortUrl.com/");
                                log.info(sb.append(urlModification.encode(lUrl)).toString());
                            }
                        } else {
                            log.info("This URL is not correct");
                        }
                        break;

                    case 2:
                        log.info("Enter you short URL");
                        String sUrl = sc.next();
                        log.info("This is your long URL:");
                        urlModification.decode(sUrl);
                        log.info(urlModification.decode(sUrl));
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
