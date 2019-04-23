package com.company;

import com.google.common.hash.Hashing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class ShortenerTest {

  //  HashMap<String, String> urlMap = new HashMap();
    String urlPrefix = "www.yourShortUrl.com/";
    Shortener s = new Shortener();


    @Test
    public void shouldEncode() {
        String longUrl = "https://javarush.ru/groups/posts/605-junit";
        s.encode(longUrl);

        String shortURL = Hashing.sha256().hashString(longUrl, StandardCharsets.UTF_8).toString();

        StringBuilder sb = new StringBuilder(urlPrefix);
        sb.append(shortURL).toString();
        //System.out.println(sb);
        //System.out.println(s);


            Assert.assertEquals(s, sb);


    }

    @Test
    public void shouldFillFile() {


    }
}