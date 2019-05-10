package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class FileFiller {

    private File file;
    MapFiller mapFiller = new MapFiller();

    public FileFiller(File file){
        this.file = file;
    }

    public void fillFile ()  {
        Iterator i = mapFiller.urlMap.entrySet().iterator();

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
