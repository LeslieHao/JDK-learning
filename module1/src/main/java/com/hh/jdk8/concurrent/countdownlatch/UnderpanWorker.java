package com.hh.jdk8.concurrent.countdownlatch;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

/**
 * 负责组装底盘
 *
 * @author HaoHao
 * @date 2020/7/14 4:34 下午
 */
public class UnderpanWorker implements Runnable {

    private final CountDownLatch workshopManager;

    public UnderpanWorker(CountDownLatch workshopManager) {
        this.workshopManager = workshopManager;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(1000 * 3);
        System.out.println("底盘组装完成~");
        workshopManager.countDown();
    }


}
