package com.company;

import com.sun.istack.internal.Nullable;

import javax.imageio.IIOException;
import java.io.*;

public class URLDecoder {

    File file = new File("/home/vikad/URL.txt");

    // Decodes a shortened URL to its original URL.
    public void decode(String shortUrl) {
        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;

            while ((s = br.readLine()) != null) {
                String str = s.split(" = ", 2)[0];
                String str1 = s.split(" = ", 2)[1];

                if (shortUrl.equals(str)) {
                    String longUrl = str1;
                    System.out.println(longUrl);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File isn't exists, please create a new one");
            System.out.println("Check the directory and name: " + file.toString());

        } catch (IIOException ex) {
            System.out.println("Can't read from file");

        } catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
