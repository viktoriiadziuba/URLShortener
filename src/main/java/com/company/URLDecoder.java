package com.company;

import java.io.*;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

public class URLDecoder {


    String homeDirectory = System.getProperty("user.home");
    File file = new File(homeDirectory + "/URL.txt");
    List<String> urls = Lists.newArrayList();

    public URLDecoder() {

        readFromFile();
    }

    public void readFromFile() {
        if (file.isFile()) {
            try {
                urls = Files.readLines(file, Charsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String s = "There isn't such URL";
        for (int i = 0; i < urls.size(); i++) {
            String str = urls.get(i).split(" = ", 2)[0];
            if (shortUrl.equals(str)) {
                s = urls.get(i).split(" = ", 2)[1];
            }
        }
        return s;
    }


}


