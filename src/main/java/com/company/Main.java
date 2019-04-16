package com.company;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Enter your long URL:");
        Scanner sc = new Scanner(System.in);
        String lUrl = sc.next();
        if(URLValidator.urlValidator(lUrl)) {

            Shortener shortUrl = new Shortener();
            System.out.println("This is your short URL:");
            shortUrl.encode(lUrl);
            Iterator i = shortUrl.urlMap.entrySet().iterator();

            while (i.hasNext()) {
                Map.Entry pairs = (Map.Entry) i.next();
            try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("URL.txt", true));
                    writer.write(pairs.toString());
                    writer.newLine();
                    writer.close();
                } catch(IOException e){
                    e.printStackTrace();
                }

            }
//
            System.out.println("Enter you short URL");
            String sUrl = sc.next();
            System.out.println("This is your long URL:");
            shortUrl.decode(sUrl);
            if (shortUrl.equals(null)){
                System.out.println("There isn't such URL");
            }

        } else {
            System.out.println("This URL is not correct");
        }

    }
}

