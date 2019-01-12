package com.zhibei.test;


import com.zhibei.NioFile.ThreadIo;
import com.zhibei.NioFile.ThreadNio;
import com.zhibei.utils.Log;
import com.zhibei.utils.ThreadPool;

public class Test0 {
    public static void main(String[] args) {
        Log.start = System.currentTimeMillis();
        for (int i = 0; i<40;i++){
            ThreadPool.submit(new ThreadIo(i));
            ThreadPool.submit(new ThreadNio(i));
        }
        ThreadPool.shutdown();
    }
}
