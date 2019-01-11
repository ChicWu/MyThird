package com.zhibei.Jedis;

import com.zhibei.utils.RedisPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class ThradJedis implements Runnable {
    static JedisPool jedisPool = null;
    private int a = 0;
    static {
        jedisPool = RedisPool.getPoolInstance();
    }

    public ThradJedis(int a) {
        this.a = a;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
//        System.out.println(System.currentTimeMillis());
        int m = 0;
        Jedis jedis = jedisPool.getResource();
//        for (int i = 0 ; i < 100 ; i++){
            List<String> hvals = jedis.hvals("hash" + a);
            for (String value: hvals ) {
                m++;
            }
//        }
        long end = System.currentTimeMillis() - start;
        System.out.println("user Time ["+end+"],num ["+m+"]");
        System.out.println(System.currentTimeMillis());
    }
}
