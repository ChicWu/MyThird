package com.zhibei.LSM;

import com.zhibei.utils.ObjectTranscoder;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

public class RedisListTest {
    private static Jedis jedis;
    static {
        jedis = new Jedis("127.0.0.1", 6379);
    }
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>(1000000);
        for (int i = 0;i< 1000000 ; i++){
            arrayList.add("https://www.baidu.com/file.html?search=germany&language=de_DE#about");
        }

        long start  = System.currentTimeMillis();
        jedis.set("ss".getBytes(),ObjectTranscoder.serialize(arrayList));
        byte[] in = jedis.get("ss".getBytes());
        System.out.println(in.length);
        System.out.println(System.currentTimeMillis()-start);
        ArrayList<String> redisList =  (ArrayList<String>)ObjectTranscoder.deserialize(in);
        System.out.println(System.currentTimeMillis()-start);
        int size = 0;
        for (String str:redisList) {
            String url = str;
//            System.out.println(url);
            size++;
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(size);
    }
}
