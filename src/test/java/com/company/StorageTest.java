package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class StorageTest {

    Storage storage = Storage.getInstance("www.yourShortUrl.com/");

    @Test
    public void shouldFillMap() {
        HashMap<String, String> urlMap1 = new HashMap();
        String urlPrefix = "www.yourShortUrl.com/";
        String longUrl = "http://tutorials.jenkov.com/java-unit-testing/asserts.html";
        StringBuilder sb = new StringBuilder(urlPrefix);
        sb.append(storage.encode(longUrl));
        urlMap1.put(sb.toString(), longUrl);

        HashMap<String, String> urlMap2;
        urlMap2 = storage.fillMap(longUrl);

        Assert.assertEquals(urlMap1, urlMap2);

    }

    @Test
    public void shouldFillKeys() {
        String key = "testKey";
        String longUrl = "https://www.google.com/search?client=ubuntu&channel=fs&q=java+redis+mockito+tests+example&ie=utf-8&oe=utf-8";

        HashMap<String, String> keysMap1 = new HashMap<>();
        keysMap1.put(longUrl, key);

        HashMap<String, String> keysMap2 = storage.fillKeys(key, longUrl);

        Assert.assertEquals(keysMap1, keysMap2);
    }
}