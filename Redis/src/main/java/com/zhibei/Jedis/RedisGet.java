package com.zhibei.Jedis;

import com.zhibei.utils.RedisPool;
import redis.clients.jedis.Jedis;


public class RedisGet {
    private static Jedis jedis ;

    public static void main(String[] args) {
        try {
            jedis = RedisPool.getPoolInstance().getResource();
            JedisApi.testString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
