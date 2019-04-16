package com.company;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class Shortener {

    HashMap<String, String> urlMap = new HashMap();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        Random rand = new Random();
        int urlLen = 6;
        char[] shortURL = new char[urlLen];
        String randChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

        for (int i = 0; i < urlLen; i++)
            shortURL[i] = randChars.charAt(rand.nextInt(randChars.length()));

        StringBuilder sb = new StringBuilder("www.yourShortUrl.com/");
        sb.append(new String(shortURL));
        System.out.println(sb);

        urlMap.put(sb.toString()+" "," " + longUrl);

        return sb.toString();
    }



    // Decodes a shortened URL to its original URL.
    public void decode(String shortUrl) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("URL.txt"));
            String s;
            while ((s = br.readLine()) != null) {
                String str = s.split(" = ", 2)[0];
                String str1 = s.split(" = ", 2)[1];

                if (shortUrl.equals(str)) {
                    String longUrl = str1;
                    System.out.println(longUrl);
                }

            }



            br.close();
        } catch(IOException e){
            e.printStackTrace();
    }

    }

}