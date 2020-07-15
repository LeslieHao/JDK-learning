package com.hh.juc;

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
                    // cpu核心线程数
                    int availProcessors = Runtime.getRuntime().availableProcessors();
                    threadPool = ThreadPoolManager.createThreadPoolExecutor("线程池", availProcessors * 2, availProcessors * 6, 1000);
                }
            }
        }
        return threadPool;
    }
}
