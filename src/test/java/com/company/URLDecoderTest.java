package com.company;

import com.google.common.hash.Hashing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

public class URLDecoderTest {

    String homeDirectory = System.getProperty("user.home");
    File file = new File(homeDirectory + "/url.txt");
    HashMap<String, String> urlMap = new HashMap();
    String urlPrefix = "www.yourShortUrl.com/";
    String longUrl = "https://javarush.ru/groups/posts/605-junit";
    //URLDecoder d = new URLDecoder();

    @Before
    public void putToTheFile(){

        String shortURL = Hashing.sha256().hashString(longUrl, StandardCharsets.UTF_8).toString();

        StringBuilder sb = new StringBuilder(urlPrefix);
        sb.append(shortURL);
       // System.out.println(sb);

        urlMap.put(sb.toString()+" "," " + longUrl);

        Iterator i = urlMap.entrySet().iterator();

        while (i.hasNext()) {
            Map.Entry pairs = (Map.Entry) i.next();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(pairs.toString());
                writer.newLine();

            } catch (IOException e){
                System.err.println("Something wrong with your file: " + e.getMessage());
            }
        }

    }

    @Test
    public void shouldDecode() {
        String shortUrl = "www.yourShortUrl.com/eb92096d26c19ca2d76de430883af111b9ecae95748ca00431128b2bae4a0f63";
       // d.decode(shortUrl);

        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;

            while ((s = br.readLine()) != null) {
                String str = s.split(" = ", 2)[0];
                String str1 = s.split(" = ", 2)[1];

                if (shortUrl.equals(str)) {
                    String longUrl = str1;
                    System.out.println(longUrl);
                }

               // Assert.assertEquals(d, str1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File isn't exists, please create a new one");
            System.out.println("Check the directory and name: " + file.toString());

        } catch (IOException exception){
            System.err.println("Something wrong with your file: " + exception.getMessage());
        }

    }
}