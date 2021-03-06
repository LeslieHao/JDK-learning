package com.hh.juc.cyclicbarrier;

import com.hh.juc.ThreadPool;
import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;

/**
 * @author HaoHao
 * @date 2020/7/15 2:34 下午
 */
public class Party {

    // party 一共邀请了三个嘉宾
    static final CyclicBarrier HOST = new CyclicBarrier(4, new BarrierAction());

    public static void main(String[] args) {
        ThreadPool.get().submit(new PopStar(HOST, "张国荣", 1000L));
        ThreadPool.get().submit(new PopStar(HOST, "刘德华", 2000L));
        ThreadPool.get().submit(new PopStar(HOST, "梁朝伟", 3000L));
        ThreadPool.get().submit(new PopStar(HOST, "王家卫", 4000L));
    }

    // BarrierAction 执行完成之后屏障后的操作才会执行
    static class BarrierAction implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("所有明星已经到场~");
            System.out.println("party 准备开始~");
            Thread.sleep(1000);
        }
    }

}
