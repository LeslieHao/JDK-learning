package com.hh.jdk8.concurrent.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HaoHao
 * @date 2020/7/13 8:06 下午
 */
public class Toilet {

    private static volatile Toilet singleToilet;

    /**
     * 三个坑
     */
    private Semaphore semaphore = new Semaphore(3);

    private AtomicInteger num = new AtomicInteger(0);


    private Toilet() {
    }

    public static Toilet get() {
        if (singleToilet == null) {
            synchronized (Toilet.class) {
                if (singleToilet == null) {
                    singleToilet = new Toilet();
                }
            }
        }
        return singleToilet;
    }

    public void in(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num.incrementAndGet();
        System.out.println("进来一个人,当前人数:"+ num.get());
    }
    public void out(){
        semaphore.release();
        num.decrementAndGet();
        System.out.println("出去一个人,当前人数:"+ num.get());
    }

}
