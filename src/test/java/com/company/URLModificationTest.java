package com.company;

import com.google.common.hash.Hashing;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class URLModificationTest {

    URLModification modification = new URLModification();

    @Test
    public void shouldEncode() {
        String s = "http://tutorials.jenkov.com/java-unit-testing/asserts.html";
        String s1 = modification.encode(s);

         String result = Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();

         Assert.assertEquals(s1, result);
    }
}