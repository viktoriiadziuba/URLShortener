package com.company;

import com.google.common.hash.Hashing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class ShortenerTest {


    @Test
    public void shouldEncode() {
        //URL which should be encoded
        String s = "http://tutorials.jenkov.com/java-unit-testing/asserts.html";

        //The result of encoding with encode() method in class Shortener
        String s1 = "ad10cc74973e41748542c471c76213b82cdd99b2df81367a3391dc5ff4f48adb";

         String result = Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();

         Assert.assertEquals(s1, result);

    }

    @Test
    public void shouldFillMap(){

    }

    @Test
    public void shouldFillFile() {


    }
}