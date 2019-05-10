package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FileFiller {

    private String homeDirectory = System.getProperty("user.home");
    private File file = new File(homeDirectory + "/URL.txt");
    public static HashMap<String, String> urlMap = new HashMap();


    public void fillFile ()  {
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

}
