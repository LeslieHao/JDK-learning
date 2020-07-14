package com.hh.jdk8.concurrent;

import java.util.concurrent.ExecutorService;

/**
 * @author HaoHao
 * @date 2020/7/13 8:30 下午
 */
public class ThreadPool {

    private static volatile ExecutorService threadPool;

    private ThreadPool() {
    }

    public static ExecutorService get() {
        if (threadPool == null) {
            synchronized (ThreadPool.class) {
                if (threadPool == null) {
                    int availProcessors = Runtime.getRuntime().availableProcessors();
                    System.out.println("内核数:" + availProcessors);
                    threadPool = ThreadPoolManager.createThreadPoolExecutor("线程池", availProcessors * 2, availProcessors * 6, 1000);
                }
            }
        }
        return threadPool;
    }
}
