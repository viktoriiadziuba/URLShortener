package com.company;

import com.google.common.hash.Hashing;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class URLModification implements Encoder, Decoder {

    private FileOperation fileReader = new FileOperation(new File(System.getProperty("user.home") + "/URL.txt"), Storage.getInstance("www.yourShortUrl.com/"));

    @Override
    public String encode(String longUrl) {
        String shortURL = Hashing.sha256().hashString(longUrl, StandardCharsets.UTF_8).toString();

        return shortURL;
    }

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
