package com.company;

import org.junit.Assert;
import org.junit.Test;

public class URLDecoderTest {

    URLDecoder urlD = new URLDecoder();

   @Test
    public void shouldDecode(){
       String shortUrl = "www.yourShortUrl.com/299e6e3ed324c3280fcfd38513025cfd8b396f470b08dacfd0a30b6fa1b95a2f";
       String str = null;
       for (int i = 0; i < urlD.urls.size(); i++) {
           String s = urlD.urls.get(i).split(" = ", 2)[0];
           if (shortUrl.equals(s)) {
               str = urlD.urls.get(i).split(" = ", 2)[1];
           }
       }

       String str1 = urlD.decode(shortUrl);

       Assert.assertEquals(str, str1);

   }
}