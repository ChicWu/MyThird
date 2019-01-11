package com.zhibei.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Redis {
    private  static JedisPool poolInstance;

    private Redis(){}

    static {
        poolInstance = RedisPool.getPoolInstance();
    }

    public static Jedis getInstance(){
        return poolInstance.getResource();
    }

    public static JedisPool getPoolInstance(){
        return poolInstance;
    }
}
