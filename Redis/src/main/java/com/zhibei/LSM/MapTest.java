package com.zhibei.LSM;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<Integer, String>(10000);
        for (int i = 0 ;i < 10000 ; i++){
            map.put(i,"map.put(i,https://www.baidu.com/file.html?search=germany&language=de_DE#about)");
        }
        int size = 0;
        long start = System.currentTimeMillis();
        for (Map.Entry<Integer, String> entry:map.entrySet()) {
            String value = entry.getValue();
            size++;
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(size);
    }
}
