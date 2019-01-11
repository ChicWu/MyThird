package com.zhibei.com.zhibei.Thread;

import com.zhibei.utils.Redis;
import com.zhibei.utils.RedisPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class PipelinThread implements Runnable{

    @Override
    public void run() {
        Jedis jedis = RedisPool.getPoolInstance().getResource();
        System.out.println("bbb"+System.currentTimeMillis());
        for (int a = 0 ;a <5;a++){
            Pipeline p = jedis.pipelined();
            long start = System.currentTimeMillis();
            for (int i = 0 ; i<1000000 ;i++){
//            p.set("https://www.baidu.com/file.html?search=germany&language=de_DE#about"+i,
//                    "https://www.baidu.com/file.html?search=germany&language=de_DE#about"+i);
//            p.set("key"+i,"value"+i);
            p.get("key"+i);
//            p.get("https://www.baidu.com/file.html?search=germany&language=de_DE#about"+i);
            }
            p.sync();
            long user = System.currentTimeMillis() -start;
            System.out.println("User Time ["+user+"]");
        }
        System.out.println(System.currentTimeMillis());

    }
}
