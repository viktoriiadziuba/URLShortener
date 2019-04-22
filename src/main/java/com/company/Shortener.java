package com.company;
import com.google.common.hash.Hashing;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Shortener {
    private static final Logger log = LoggerFactory.getLogger(Shortener.class);

    HashMap<String, String> urlMap = new HashMap();
    String urlPrefix = "www.yourShortUrl.com/";
    String homeDirectory = System.getProperty("user.home");
    File file = new File(homeDirectory + "/URL.txt");


    // Encodes a URL to a shortened URL with chars from variable randChars above.
    public String encode(String longUrl) {

       String shortURL = Hashing.sha256().hashString(longUrl, StandardCharsets.UTF_8).toString();

       log.info(shortURL);
        StringBuilder sb = new StringBuilder(urlPrefix);
        sb.append(shortURL);
        System.out.println(sb);

        urlMap.put(sb.toString()+" "," " + longUrl);

        return shortURL;
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
