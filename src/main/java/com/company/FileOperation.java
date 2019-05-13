package com.company;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FileOperation {

    private File file;
    Storage storage = new Storage();
    public static List<String> urls = Lists.newArrayList();

    public FileOperation(File file) {
        this.file = file;
        readFromFile();
    }

    public void fillFile () {
        Iterator i = storage.urlMap.entrySet().iterator();

        while (i.hasNext()) {
            Map.Entry pairs = (Map.Entry) i.next();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(pairs.toString());
                writer.newLine();

            } catch (IOException e) {
                System.err.println("Something wrong with your file: " + e.getMessage());
            }
        }
    }

    public HashMap<String, String> readFromFile() {
        HashMap<String, String> mapFile = new HashMap<>();
        if (file.isFile()) {
            try {
                urls = Files.readLines(file, Charsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < urls.size(); i++) {
                String str = urls.get(i).split(" = ", 2)[0];
                String str1 = urls.get(i).split(" = ", 2)[1];
                mapFile.put(str, str1);
            }
        }
        return mapFile;
    }
}
