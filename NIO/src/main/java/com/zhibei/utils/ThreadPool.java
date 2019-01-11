package com.zhibei.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池工具类
 * TODO 增加代理模式
 */
public class ThreadPool {
    private static final ExecutorService executorService;

    private ThreadPool() {
    }

    static{
        executorService= Executors.newFixedThreadPool(80);
    }

    public static Future<?> submit(Runnable task){
        return executorService.submit(task);
    }

    public static <T> Future<T> submit(Callable<T> task){
        return executorService.submit(task);
    }

    public static ExecutorService getExecutorService(){
        return executorService;
    }

    public static void shutdown(){
        executorService.shutdown();
    }

}
