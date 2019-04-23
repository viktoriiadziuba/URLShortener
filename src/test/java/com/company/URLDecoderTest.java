package com.company;

import org.junit.Assert;
import org.junit.Test;

public class URLDecoderTest {

    URLDecoder urlD = new URLDecoder();

   @Test
    public void shouldDecode(){
       String shortUrl = "www.yourShortUrl.com/f9ab1e0018c9987732d9ee858596352c237520878115eb003d942b75e17b67a6";
       String str = null;
       for (int i = 0; i < urlD.urls.size(); i++) {
           str = urlD.urls.get(i).split(" = ", 2)[1];
       }

       String str1 = urlD.decode(shortUrl);

       Assert.assertEquals(str, str1);

   }
}