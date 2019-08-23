package com.company;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import java.util.*;

public class DBOperation {

    private static final String redisHost = "localhost";
    private static final Integer redisPort = 6379;
    private static JedisPool pool = null;
    Storage storage;

    public DBOperation(Storage storage) {
        this.storage = storage;
        pool = new JedisPool(redisHost, redisPort);
    }

    public void fillDB () {
        Iterator i = Storage.urlMap.entrySet().iterator();
        Set<String> keys = Storage.secretKeys.keySet();

        for (String key : keys) {
            while (i.hasNext()) {
                Map.Entry pairs = (Map.Entry) i.next();
                String entryKey = pairs.getKey().toString();
                String entryValue = pairs.getValue().toString();
                HashMap<String, String> forDB = new HashMap<>();
                forDB.put(entryKey, entryValue);

                if(key.equals(pairs.getValue().toString())) {
                    Jedis jedis = pool.getResource();
                    try {
                        jedis.hmset(Storage.secretKeys.get(key), forDB);
                    } catch (JedisException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public HashMap<String, String> readFromDB(String key) {
        HashMap<String, String> mapDB = new HashMap<>();

        Jedis jedis = pool.getResource();
        try {
            Map<String, String> retrieveMap = jedis.hgetAll(key);
            Iterator i = retrieveMap.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry pairs = (Map.Entry) i.next();
                String entryKey = pairs.getKey().toString();
                String entryValue = pairs.getValue().toString();
                mapDB.put(entryKey, entryValue);
            }
        } catch (JedisException e) {
            e.printStackTrace();
        }
        return mapDB;
    }

    public String deleteFromDB(String key) {
        String result = "This key is not correct";
        Jedis jedis = pool.getResource();
        try {
            jedis.del(key);
            result = "Your URLs are successfully deleted";
        } catch (JedisException e) {
            e.printStackTrace();
        }
        return result;
    }
}
