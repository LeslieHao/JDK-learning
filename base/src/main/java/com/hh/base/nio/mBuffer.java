package com.hh.base.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author HaoHao
 * @date 2020/4/23 3:50 下午
 */
public class mBuffer {

    @Test
    public void test() {
        // 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 看一下初始时4个核心变量的值
        // 界限,标识缓冲区可以操作数据的大小
        System.out.println("初始时-->limit--->" + byteBuffer.limit());
        // 表示缓冲区中正在操作数据的位置
        System.out.println("初始时-->position--->" + byteBuffer.position());
        // 容量,表示缓冲区中最大存储数据的容量,声明后不能改变
        System.out.println("初始时-->capacity--->" + byteBuffer.capacity());
        // 记录,可以通过reset() 把position 恢复的mark 位置
        System.out.println("初始时-->mark--->" + byteBuffer.mark());

        // 添加一些数据导缓冲区
        String str = "Hello Nio~";
        byteBuffer.put(str.getBytes());

        // 4个核心变量的值
        System.out.println("put 后-->limit--->" + byteBuffer.limit());
        System.out.println("put 后-->position--->" + byteBuffer.position());
        System.out.println("put 后-->capacity--->" + byteBuffer.capacity());
        System.out.println("put 后-->mark--->" + byteBuffer.mark());

        // 可以认为flip 是讲缓冲区切换到读模式
        byteBuffer.flip();

        System.out.println("flip 后-->limit--->" + byteBuffer.limit());
        System.out.println("flip 后-->position--->" + byteBuffer.position());
        System.out.println("flip 后-->capacity--->" + byteBuffer.capacity());
        System.out.println("flip 后-->mark--->" + byteBuffer.mark());


        // 读数据
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));

        System.out.println("读后 -->limit--->" + byteBuffer.limit());
        System.out.println("读后 -->position--->" + byteBuffer.position());
        System.out.println("读后 -->capacity--->" + byteBuffer.capacity());
        System.out.println("读后 -->mark--->" + byteBuffer.mark());

        // 清空缓冲区(切换到写模式)
        byteBuffer.clear();

        System.out.println("clear 后-->limit--->" + byteBuffer.limit());
        System.out.println("clear 后-->position--->" + byteBuffer.position());
        System.out.println("clear 后-->capacity--->" + byteBuffer.capacity());
        System.out.println("clear 后-->mark--->" + byteBuffer.mark());

    }


}
