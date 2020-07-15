package com.hh.juc;


import com.hh.common.type.User;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author HaoHao
 * @date 2019-06-25 18:06
 */
public class mAtomic {

    AtomicInteger aInt = new AtomicInteger();

    // 修改引用时线程安全
    AtomicReference<User> aUser = new AtomicReference<>();

    // 对象修改属性修改线程安全
    AtomicIntegerFieldUpdater<User> aField = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    // 解决ABA 问题
    int num = 0;
    AtomicStampedReference<Integer> aStamped = new AtomicStampedReference<Integer>(num, 0);

}
