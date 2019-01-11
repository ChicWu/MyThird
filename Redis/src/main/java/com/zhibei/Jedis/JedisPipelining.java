package com.zhibei.Jedis;

import com.zhibei.utils.Redis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class JedisPipelining {
    public static void main(String[] args) {
        Jedis jedis = Redis.getInstance();
        Pipeline p = jedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0 ; i<1000000 ;i++){
            p.set("https://www.baidu.com/file.html?search=germany&language=de_DE#about"+i,"https://www.baidu.com/file.html?search=germany&language=de_DE#about"+i);
//            Response<String> stringResponse = p.get("key" + i);
        }
        p.sync();
        long user = System.currentTimeMillis() -start;
        System.out.println("User Time ["+user+"]");
    }
}
