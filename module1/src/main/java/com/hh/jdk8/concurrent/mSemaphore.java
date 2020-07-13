package com.hh.jdk8.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 信号量
 *
 * @author HaoHao
 * @date 2020/7/13 8:02 下午
 */
public class mSemaphore {

    Semaphore semaphore = new Semaphore(10);

    Semaphore fairSemaphore = new Semaphore(10, true);


}
