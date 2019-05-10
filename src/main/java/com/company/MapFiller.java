package com.company;

import java.util.HashMap;

import static com.company.FileFiller.urlMap;

public class MapFiller extends Shortener {

    private String urlPrefix = "www.yourShortUrl.com/";

    public HashMap<String, String> fillMap(String longUrl){
        StringBuilder sb = new StringBuilder(urlPrefix);
        sb.append(encode(longUrl));
        urlMap.put(sb.toString()+" "," " + longUrl);

        return urlMap;
    }

    public String printShortUrl(String lUrl){
        StringBuilder sb = new StringBuilder(urlPrefix);
        sb.append(encode(lUrl));
        return sb.toString();
    }

}
