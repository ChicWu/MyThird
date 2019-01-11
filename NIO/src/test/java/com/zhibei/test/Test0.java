//package com.zhibei.test;
//
//
//import com.zhibei.Thread.ThreadIo;
//import com.zhibei.Thread.ThreadNio;
//import com.zhibei.utils.ThreadPool;
//
//public class Test0 {
//    public static long start ;
//    public static void main(String[] args) {
//        start = System.currentTimeMillis();
//        for (int i = 0; i<50;i++){
//            ThreadPool.submit(new ThreadIo());
//            ThreadPool.submit(new ThreadNio());
//        }
//    }
//}
