package com.hh.juc.semaphore;

import com.hh.juc.ThreadPool;
import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;

/**
 * @author HaoHao
 * @date 2020/7/13 8:21 下午
 */
public class Monitor {


    public static void main(String[] args) {
        Toilet toilet = Toilet.get();
        ExecutorService executor = ThreadPool.get();
        executor.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    toilet.in();
                    Thread.sleep(100);
                }
            }
        });

        executor.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    toilet.out();
                    Thread.sleep(1000);
                }
            }
        });
    }
}
