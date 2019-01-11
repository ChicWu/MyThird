package com.zhibei.LSM;

import java.util.ArrayList;

public class ListTest {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>(10000);
        for (int i = 0;i< 10000 ; i++){
            arrayList.add("https://www.baidu.com/file.html?search=germany&language=de_DE#about");
        }
        int size = 0;
        long start  = System.currentTimeMillis();
        for (String str:arrayList) {
            String url = str;
            size++;
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(size);
    }
}
