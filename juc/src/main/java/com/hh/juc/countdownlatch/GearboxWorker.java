package com.hh.juc.countdownlatch;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

/**
 * 负责组装变速箱
 *
 * @author HaoHao
 * @date 2020/7/14 4:34 下午
 */
public class GearboxWorker implements Runnable {


    private final CountDownLatch workshopManager;

    public GearboxWorker(CountDownLatch workshopManager) {
        this.workshopManager = workshopManager;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(1000 * 4);
        System.out.println("变速箱组装完成~");
        workshopManager.countDown();
    }


}
