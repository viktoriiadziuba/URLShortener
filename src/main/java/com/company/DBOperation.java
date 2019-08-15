package com.company;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileOperation {

    private static final Logger log = LoggerFactory.getLogger(FileOperation.class);

    private static final String redisHost = "localhost";
    private static final Integer redisPort = 6379;
    private static JedisPool pool = null;

    Storage storage;
    public static List<String> urls = Lists.newArrayList();

    public FileOperation(Storage storage) {
        this.storage = storage;
        pool = new JedisPool(redisHost, redisPort);
    }

    public void fillFile () {
        log.info("Started");
        Iterator i = Storage.getInstance("www.yourShortUrl.com/").urlMap.entrySet().iterator();
        Set<String> keys = Storage.secretKeys.keySet();
        for (String key : keys) {
            log.info("Keys checking");
            while (i.hasNext()) {
                log.info("while");
                Map.Entry pairs = (Map.Entry) i.next();
                String entryKey = pairs.getKey().toString();
                String entryValue = pairs.getValue().toString();
                log.info(key);
                log.info(pairs.getValue().toString());
                HashMap<String, String> forDB = new HashMap<>();
                forDB.put(entryKey, entryValue);
                if(key.equals(pairs.getValue().toString())) {
                    log.info("Equals");
                    Jedis jedis = pool.getResource();
                    try {
                        jedis.hmset(Storage.secretKeys.get(key), forDB);
                       log.info("Saved");
                    } catch (JedisException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

//    public HashMap<String, String> readFromFile() {
//        HashMap<String, String> mapFile = new HashMap<>();
//        if (file.isFile()) {
//            try {
//                urls = Files.readLines(file, Charsets.UTF_8);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            for (int i = 0; i < urls.size(); i++) {
//                String str = urls.get(i).split(" = ", 2)[0];
//                String str1 = urls.get(i).split(" = ", 2)[1];
//                mapFile.put(str, str1);
//            }
//        }
//        return mapFile;
//    }
}
