package com.company;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Shortener implements Encoder {

    @Override
    public String encode(String longUrl) {
        String shortURL = Hashing.sha256().hashString(longUrl, StandardCharsets.UTF_8).toString();

        return shortURL;
    }

}
