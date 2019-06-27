package com.hh.jdk8.concurrent.producer;

/**
 * 生产者
 *
 * @author HaoHao
 * @date 2019-06-26 15:33
 */
public class Producer implements Runnable {


    @Override
    public void run() {
        StoreHouse storeHouse = StoreHouse.getInstance();
        System.out.println("生产者:"+Thread.currentThread().getName()+"开始生产~");
        for (;;){
            storeHouse.lockDeposit();
            //storeHouse.deposit();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
