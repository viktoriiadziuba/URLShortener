package com.company;


import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Shortener {

    HashMap<String, String> urlMap = new HashMap();
    String urlPrefix = "www.yourShortUrl.com/";
    String randChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    String homeDirectory = System.getProperty("user.home");
    File file = new File(homeDirectory + "/URL.txt");


    // Encodes a URL to a shortened URL with chars from variable randChars above.
    public String encode(String longUrl) {
        Random rand = new Random();
        int urlLen = 6;
        char[] shortURL = new char[urlLen];

        for (int i = 0; i < urlLen; i++)
            shortURL[i] = randChars.charAt(rand.nextInt(randChars.length()));

        StringBuilder sb = new StringBuilder(urlPrefix);
        sb.append(new String(shortURL));
        System.out.println(sb);

        urlMap.put(sb.toString()+" "," " + longUrl);

        return sb.toString();
    }

    public void fillFile ()  {
        Iterator i = urlMap.entrySet().iterator();

        while (i.hasNext()) {
            Map.Entry pairs = (Map.Entry) i.next();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(pairs.toString());
                writer.newLine();

            } catch (IOException e){
                System.err.println("Something wrong with your file: " + e.getMessage());
            }
        }
    }

    }
