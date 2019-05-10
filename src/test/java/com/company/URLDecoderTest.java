package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class URLDecoderTest {

    URLDecoder urlD = new URLDecoder();
    FileReader fileReader = new FileReader(new File(System.getProperty("user.home") + "/URL.txt"));


    @Test
    public void shouldDecode(){
       String shortUrl = "www.yourShortUrl.com/4a6abf377cd12cf0df0579c39dbff91b397d69f9a354c48e460dd85826246d1e";
       String str = null;
       for (int i = 0; i < fileReader.urls.size(); i++) {
           String s = fileReader.urls.get(i).split(" = ", 2)[0];
           if (shortUrl.equals(s)) {
               str = fileReader.urls.get(i).split(" = ", 2)[1];
           }
       }

        String str1 = urlD.decode(shortUrl);

       Assert.assertEquals(str, str1);

   }
}