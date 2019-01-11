package com.zhibei.Demo;

import com.zhibei.utils.ThreadPool;

public class main {
    public static void main(String[] args) {
        ThreadPool.submit(new Service());
        ThreadPool.submit(new Client());
        ThreadPool.shutdown();
    }
}
