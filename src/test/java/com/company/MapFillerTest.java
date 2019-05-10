package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class MapFillerTest {

    MapFiller mapFiller = new MapFiller();

    @Test
    public void fillMap() {
        HashMap<String, String> urlMap1 = new HashMap();
        String urlPrefix = "www.yourShortUrl.com/";
        String longUrl = "http://tutorials.jenkov.com/java-unit-testing/asserts.html";
        StringBuilder sb = new StringBuilder(urlPrefix);
        sb.append(mapFiller.encode(longUrl));
        urlMap1.put(sb.toString()+" "," " + longUrl);

        HashMap<String, String> urlMap2;
        urlMap2 = mapFiller.fillMap(longUrl);

        Assert.assertEquals(urlMap1, urlMap2);
    }
}