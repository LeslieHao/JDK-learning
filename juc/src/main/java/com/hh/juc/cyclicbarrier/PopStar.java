package com.hh.juc.cyclicbarrier;

import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;

/**
 * 明星
 *
 * @author HaoHao
 * @date 2020/7/15 2:31 下午
 */
public class PopStar implements Runnable {

    // 主持人
    private CyclicBarrier host;

    // 姓名
    private String name;

    // 迟到时间
    private Long lateTime;

    public PopStar(CyclicBarrier host, String name, Long lateTime) {
        this.host = host;
        this.name = name;
        this.lateTime = lateTime;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(lateTime);
        System.out.println(name + "入场~");
        host.await();
        System.out.println(name + "开始嗨了~");
    }
}
