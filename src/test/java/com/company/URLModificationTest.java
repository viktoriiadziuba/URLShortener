package com.company;

import com.google.common.hash.Hashing;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static org.junit.Assert.*;

public class URLModificationTest {

    URLModification modification = new URLModification();
    String homeDirectory = System.getProperty("user.home");
    FileOperation fileOperation = new FileOperation(new File(homeDirectory + "/URL.txt"), new Storage("www.yourShortUrl.com/"));
    HashMap urls = fileOperation.readFromFile();

    @Test
    public void shouldEncode() {
        String s = "http://tutorials.jenkov.com/java-unit-testing/asserts.html";
        String s1 = modification.encode(s);

         String result = Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();

         Assert.assertEquals(s1, result);
    }

    @Test
    public void decode() {
        String shortUrl = "www.yourShortUrl.com/4a6abf377cd12cf0df0579c39dbff91b397d69f9a354c48e460dd85826246d1e";
       String str = urls.get(shortUrl).toString();

        String str1 = modification.decode(shortUrl);

       Assert.assertEquals(str, str1);
    }
}