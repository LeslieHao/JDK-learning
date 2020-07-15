package com.hh.juc.countdownlatch;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

/**
 * 负责组装发动机
 *
 * @author HaoHao
 * @date 2020/7/14 4:34 下午
 */
public class EngineWorker implements Runnable {

    private final CountDownLatch workshopManager;

    public EngineWorker(CountDownLatch workshopManager) {
        this.workshopManager = workshopManager;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(1000 * 5);
        System.out.println("发动机组装完成~");
        workshopManager.countDown();
    }


}
