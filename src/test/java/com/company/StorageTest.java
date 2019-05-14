package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class StorageTest {

    Storage storage = Storage.getInstance("www.yourShortUrl.com/");

    @Test
    public void shouldFillMap() {
        HashMap<String, String> urlMap1 = new HashMap();
        String urlPrefix = "www.yourShortUrl.com/";
        String longUrl = "http://tutorials.jenkov.com/java-unit-testing/asserts.html";
        StringBuilder sb = new StringBuilder(urlPrefix);
        sb.append(storage.encode(longUrl));
        urlMap1.put(sb.toString()+" "," " + longUrl);

        HashMap<String, String> urlMap2;
        urlMap2 = storage.fillMap(longUrl);

        Assert.assertEquals(urlMap1, urlMap2);

    }
}