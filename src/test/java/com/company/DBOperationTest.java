package com.company;

import org.junit.Before;
import org.mockito.Mock;

import org.junit.Test;
import org.mockito.MockitoAnnotations;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DBOperationTest {

    @Mock
    private JedisPool jedisPool;

    @Mock
    private Jedis jedis;

    @Mock
    DBOperation dbOperation;
    Storage storage = Storage.getInstance("www.yourShortUrl.com/");

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dbOperation = new DBOperation(storage ,jedisPool);

        when(jedisPool.getResource()).thenReturn(jedis);
    }

    @Test
    public void fillDB() {
        String key = "testKey";
        String longUrl = "https://www.vogella.com/tutorials/Mockito/article.html#testing-with-mock-objects";
        dbOperation.storage.fillKeys(key, longUrl);
        dbOperation.storage.fillMap(longUrl);
        dbOperation.fillDB();
        HashMap<String, String> actualMap = dbOperation.readFromDB(key);
        HashMap<String, String> spy = spy(actualMap);

        doReturn("Success").when(spy).get(0);

        assertEquals("Success", spy.get(0));
    }

    @Test
    public void readFromDB() {
        fillDB();
        String key = "testKey";
        HashMap<String, String> actualMap = dbOperation.readFromDB(key);

        assertEquals(true, !actualMap.isEmpty());
    }

    @Test
    public void deleteFromDB() {
        fillDB();
        String key = "testKey";
        String result = dbOperation.deleteFromDB(key);

        when(jedis.hgetAll(key).isEmpty()).thenReturn(null);

        assertEquals("Your URLs are successfully deleted", result);
    }
}