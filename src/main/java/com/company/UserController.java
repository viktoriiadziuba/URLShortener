package com.company;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.slf4j.Logger;
import redis.clients.jedis.JedisPool;

public class UserController {

    Storage storage = Storage.getInstance("www.yourShortUrl.com/");
    URLModification urlModification = new URLModification();
    private static final String redisHost = "localhost";
    private static final Integer redisPort = 6379;
    JedisPool pool = new JedisPool(redisHost, redisPort);
    DBOperation DBOperation = new DBOperation(storage, pool);
    Scanner scan;
    Logger log;

    public UserController(Logger log, Scanner scan) {
        this.log = log;
        this.scan = scan;
    }

    public void start() {
        while (true) {
            log.info("Please choose what you would like to do with your URL:");
            log.info("In case if you want to make your URL shorter, please enter 1");
            log.info("In case if you want to get your original URL by its short version, please enter 2");
            log.info("In case if you want to delete your URLs by tour secret word, please enter 3");
            log.info("If you would like to stop the application, please press CTRL + C");
            log.info("Please enter your choice:");

            try {
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        log.info("Enter your long URL:");
                        String lUrl = scan.next();
                        if (URLValidator.urlValidator(lUrl)) {
                            log.info("Please enter your secret key: ");
                            String key = scan.next();
                            storage.fillKeys(key, lUrl);
                            log.info("This is your short URL:");
                            urlModification.encode(lUrl);
                            storage.fillMap(lUrl);
                            StringBuilder sb = new StringBuilder("www.yourShortUrl.com/");
                            log.info(sb.append(urlModification.encode(lUrl)).toString());
                        } else {
                            log.info("This URL is not correct");
                        }
                        break;

                    case 2:
                        log.info("Enter your secret key: ");
                        String key = scan.next();
                        log.info("Enter your short URL: ");
                        String sUrl = scan.next();
                        log.info("This is your long URL:");
                        HashMap<String, String> map = DBOperation.readFromDB(key);
                        log.info(map.get(sUrl));
                        break;
//                        } else {
//                            log.info("There isn't any URL, please create a new one");
//                        }

                    case 3:
                        log.info("Enter your secret word: ");
                        String secretKey = scan.next();
                        log.info(DBOperation.deleteFromDB(secretKey));

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
