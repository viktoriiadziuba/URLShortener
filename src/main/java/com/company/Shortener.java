package com.company;

import com.google.common.hash.Hashing;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Shortener {


    HashMap<String, String> urlMap = new HashMap();
    String urlPrefix = "www.yourShortUrl.com/";
    String homeDirectory = System.getProperty("user.home");
    File file = new File(homeDirectory + "/URL.txt");
    URLDecoder decoder = new URLDecoder();


    public String encode(String longUrl) {
        String shortURL = Hashing.sha256().hashString(longUrl, StandardCharsets.UTF_8).toString();

        return shortURL;
    }

    public void fillMap(String longUrl){
        StringBuilder sb = new StringBuilder(urlPrefix);
        sb.append(encode(longUrl));
        System.out.println(sb);
        urlMap.put(sb.toString()+" "," " + longUrl);

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
