package com.hh.jdk8.container;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 阻塞队列
 *
 * @author HaoHao
 * @date 2020/7/10 4:46 下午
 */
public class mBlockQueue {

    LinkedBlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>();

    ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
    


}
