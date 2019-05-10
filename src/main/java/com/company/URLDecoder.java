package com.company;

import java.io.File;

public class URLDecoder implements Decoder {

    FileReader fileReader = new FileReader(new File(System.getProperty("user.home") + "/URL.txt"));

    // Decodes a shortened URL to its original URL.
    @Override
    public String decode(String shortUrl) {
        String s = "There isn't such URL";
        for (int i = 0; i < fileReader.urls.size(); i++) {
            String str = fileReader.urls.get(i).split(" = ", 2)[0];
            if (shortUrl.equals(str)) {
                s = fileReader.urls.get(i).split(" = ", 2)[1];
            }
        }
        return s;
    }


}


