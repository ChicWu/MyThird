package com.zhibei.test;

import com.zhibei.Jedis.ThradJedis;
import com.zhibei.utils.RedisPool;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1 {
    private static final Jedis jedis ;
    private static final ExecutorService ThrowsPool;

    static {
        jedis = RedisPool.getPoolInstance().getResource();
        ThrowsPool = Executors.newFixedThreadPool(10);
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0 ; i < 10000 ; i++){
            String key = "key"+i;
            String value = "value"+i;
            map.put( key,value);
        }
        for (int i = 0 ;i <600 ;i++){
            String key = "hash"+i;
            jedis.hmset(key, map);
        }

        System.out.println(System.currentTimeMillis());
        for (int i = 0 ; i <600 ;i++){
            ThrowsPool.submit(new ThradJedis(i));
        }
    }
}
