package com.hh.juc.producer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 仓库
 *
 * @author HaoHao
 * @date 2019-06-26 15:34
 */
public class StoreHouse {

    // 储备
    public volatile AtomicInteger reserve = new AtomicInteger(0);

    // 最大存储量
    public static final int MAX_RESERVE = 1000;

    private static StoreHouse singleStore;

    private StoreHouse() {
    }

    public static StoreHouse getInstance() {
        if (singleStore == null) {
            synchronized (StoreHouse.class) {
                if (singleStore == null) {
                    singleStore = new StoreHouse();
                }
            }
        }
        return singleStore;
    }

    /**
     * 存
     */
    synchronized void deposit() {
        while (reserve.get() >= 1000) {
            System.out.println("仓库存满啦,先别生产了~生产者:" + Thread.currentThread().getName() + "等待");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 存入
        reserve.incrementAndGet();
        System.out.println("生产者:" + Thread.currentThread().getName() + "存入一件商品成功");
        System.out.println("当前库存:" + reserve.get());
        // 唤醒消费者
        notifyAll();
    }

    /**
     * 取
     */
    void take() {
        synchronized (singleStore) {
            while (reserve.get() <= 0) {
                System.out.println("仓库不足~消费者:" + Thread.currentThread().getName() + "等待");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 取出
            reserve.decrementAndGet();
            System.out.println("消费者:" + Thread.currentThread().getName() + "购买一件商品成功");
            System.out.println("当前库存:" + reserve.get());
            notifyAll();
        }
    }

    private final ReentrantLock lock = new ReentrantLock();
    private Condition empty = lock.newCondition();
    private Condition full = lock.newCondition();

    /**
     * 存
     */
    synchronized void lockDeposit() {
        lock.lock();
        try {
            while (reserve.get() >= 1000) {
                System.out.println("仓库存满啦,先别生产了~生产者:" + Thread.currentThread().getName() + "等待");
                full.await();
            }
            // 存入
            reserve.incrementAndGet();
            System.out.println("生产者:" + Thread.currentThread().getName() + "存入一件商品成功");
            System.out.println("当前库存:" + reserve.get());
            // 唤醒一个消费者
            empty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void lockTake() {
        lock.lock();
        try {
            while (reserve.get() <= 0) {
                System.out.println("仓库不足~消费者:" + Thread.currentThread().getName() + "等待");
                empty.await();
            }
            // 取出
            reserve.decrementAndGet();
            System.out.println("消费者:" + Thread.currentThread().getName() + "购买一件商品成功");
            System.out.println("当前库存:" + reserve.get());
            // 唤醒一个生产者
            full.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
