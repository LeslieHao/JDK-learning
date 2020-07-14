package com.hh.jdk8.concurrent.countdownlatch;

import com.hh.jdk8.concurrent.ThreadPool;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * Mercedes Benz A45 AMG 组装车间
 *
 * @author HaoHao
 * @date 2020/7/14 4:42 下午
 */
public class WorkShop {

    static final CountDownLatch WORK_SHOP_MANAGER = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = ThreadPool.get();
        System.out.println("组装任务开始下发~ time:" + LocalDateTime.now());
        executor.submit(new EngineWorker(WORK_SHOP_MANAGER));
        executor.submit(new UnderpanWorker(WORK_SHOP_MANAGER));
        executor.submit(new GearboxWorker(WORK_SHOP_MANAGER));
        System.out.println("组装任务下发完毕~ time:" + LocalDateTime.now());
        WORK_SHOP_MANAGER.await();
        System.out.println("车辆组装完成~ time:" + LocalDateTime.now());
    }
}
