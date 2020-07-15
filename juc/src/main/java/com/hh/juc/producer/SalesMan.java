package com.hh.juc.producer;

/**
 * 售货员
 *
 * @author HaoHao
 * @date 2019-06-27 11:12
 */
public class SalesMan {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Producer());
            thread.setName("生产者" + i);
            thread.start();
        }
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Consumer());
            thread.setName("消费者" + i);
            thread.start();
        }

    }
}
