package com.hh.juc.producer;

/**
 * 消费者
 *
 * @author HaoHao
 * @date 2019-06-26 15:33
 */
public class Consumer implements Runnable {


    @Override
    public void run() {
        StoreHouse storeHouse = StoreHouse.getInstance();
        System.out.println("消费者:" + Thread.currentThread().getName() + "开始消费~");
        for (; ; ) {
            storeHouse.lockTake();
            //storeHouse.take();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
