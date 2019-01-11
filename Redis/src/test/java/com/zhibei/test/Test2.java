package com.zhibei.test;

import com.zhibei.com.zhibei.Thread.PipelinThread;
import com.zhibei.com.zhibei.Thread.PipelinThread2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test2 {
    private static final ExecutorService ThrowsPool;
    static {
        ThrowsPool = Executors.newFixedThreadPool(10);
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
          ThrowsPool.submit(new PipelinThread());
          ThrowsPool.submit(new PipelinThread2());
    }
}
