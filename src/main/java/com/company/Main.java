package com.company;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Shortener shortUrl = new Shortener();
        URLDecoder longUrl = new URLDecoder();
        Scanner sc = new Scanner(System.in);
        String b = "";

        while (!b.equalsIgnoreCase("stop")) {

            System.out.println("Please choose what you would like to do with your URL:");
            System.out.println("In case if you want to make your URL shorter, please enter 1");
            System.out.println("In case if you want to get your original URL by its short version, please enter 2");
            System.out.println("If you would like to stop the application, please enter 3");
            System.out.println("Please enter your choice:");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter your long URL:");
                    String lUrl = sc.next();
                    if (URLValidator.urlValidator(lUrl)) {
                        System.out.println("This is your short URL:");
                        shortUrl.encode(lUrl);
                    } else {
                        System.out.println("This URL is not correct");
                    }
                    break;

                case 2:
                    System.out.println("Enter you short URL");
                    String sUrl = sc.next();
                    System.out.println("This is your long URL:");
                    longUrl.decode(sUrl);
                    break;

                case 3:
                    System.out.println("Just write the word 'stop' ");
                    b = sc.next();
                    shortUrl.fillFile();
            }

        }
    }
}

