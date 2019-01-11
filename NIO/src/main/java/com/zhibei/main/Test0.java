package com.zhibei.main;


import com.zhibei.Thread.ThreadIo;
import com.zhibei.Thread.ThreadNio;
import com.zhibei.utils.ThreadPool;

public class Test0 {
    public static long start ;
    public static void main(String[] args) {
        start = System.currentTimeMillis();
        for (int i = 0; i<40;i++){
            ThreadPool.submit(new ThreadIo(i));
            ThreadPool.submit(new ThreadNio(i));
//            ThreadPool.submit(new ThreadIo());
//            ThreadPool.submit(new ThreadNio());
        }
        ThreadPool.shutdown();
    }
}
