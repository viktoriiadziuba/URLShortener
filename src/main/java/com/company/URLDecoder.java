package com.company;

import java.io.*;

public class URLDecoder {

    String homeDirectory = System.getProperty("user.home");

    File file = new File(homeDirectory + "/URL.txt");

    // Decodes a shortened URL to its original URL.
    public void decode(String shortUrl) {
        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;

            while ((s = br.readLine()) != null) {
                String str = s.split(" = ", 2)[0];
                String str1 = s.split(" = ", 2)[1];

                if (shortUrl.equals(str)) {
                    String longUrl = str1;
                    System.out.println(longUrl);
                }


            }
        } catch (FileNotFoundException e) {
            System.out.println("File isn't exists, please create a new one");
            System.out.println("Check the directory and name: " + file.toString());

        } catch (IOException exception){
            System.err.println("Something wrong with your file: " + exception.getMessage());
        }
    }
}
