package com.company;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class FileReader {

    private File file;
    public static List<String> urls = Lists.newArrayList();

    public FileReader(File file){
        this.file = file;
        readFromFile();
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
