package com.company;

import java.util.HashMap;

public class MapFiller extends Shortener {

    private String urlPrefix = "www.yourShortUrl.com/";
    public static HashMap<String, String> urlMap = new HashMap();

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public HashMap<String, String> fillMap(String longUrl){
        StringBuilder sb = new StringBuilder(urlPrefix);
        sb.append(encode(longUrl));
        urlMap.put(sb.toString()+" "," " + longUrl);

        return urlMap;
    }


}
