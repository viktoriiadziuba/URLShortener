package com.company;

import com.google.common.hash.Hashing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class ShortenerTest {

    Shortener sh = new Shortener();


    @Test
    public void shouldEncode() {
        String s = "http://tutorials.jenkov.com/java-unit-testing/asserts.html";
        String s1 = sh.encode(s);

         String result = Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();

         Assert.assertEquals(s1, result);

    }

    @Test
    public void shouldFillMap(){
        HashMap<String, String> urlMap1 = new HashMap();
        String urlPrefix = "www.yourShortUrl.com/";
        String longUrl = "http://tutorials.jenkov.com/java-unit-testing/asserts.html";
        StringBuilder sb = new StringBuilder(urlPrefix);
        sb.append(sh.encode(longUrl));
        urlMap1.put(sb.toString()+" "," " + longUrl);

        HashMap<String, String> urlMap2;
        sh.fillMap(longUrl);
        urlMap2 = sh.urlMap;

        Assert.assertEquals(urlMap1, urlMap2);
    }

}