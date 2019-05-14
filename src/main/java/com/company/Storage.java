package com.company;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Storage implements Encoder {

    private String urlPrefix;
    public static HashMap<String, String> urlMap = new HashMap();
    private static final Storage instanceStorage = new Storage();

   private Storage() {
       this.urlPrefix = "www.yourShortUrl.com/";
   }


    @Override
    public String encode(String longUrl) {
        String shortURL = Hashing.sha256().hashString(longUrl, StandardCharsets.UTF_8).toString();

        return shortURL;
    }

    public HashMap<String, String> fillMap(String longUrl) {
        StringBuilder sb = new StringBuilder(urlPrefix);
        sb.append(encode(longUrl));
        urlMap.put(sb.toString()+" "," " + longUrl);

        return urlMap;
    }

    public static Storage getInstance(){
       return instanceStorage;
    }
}
