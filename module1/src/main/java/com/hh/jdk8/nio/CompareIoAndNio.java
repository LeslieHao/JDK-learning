package com.hh.jdk8.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author HaoHao
 * @date 2020/4/23 2:45 下午
 */
public class CompareIoAndNio {

    static long copyFileByIo(File soruce, File target) throws IOException {
        long startTime = System.currentTimeMillis();
        if (!target.exists()) {
            target.createNewFile();
        } else {
            target.delete();
        }
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(soruce));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target));

        // mb 读写
        byte[] bytes = new byte[1024 * 1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        long endTime = System.currentTimeMillis();
        long timeWaste = endTime - startTime;
        System.out.println("IO 耗时:" + (timeWaste));
        return timeWaste;
    }

    static long copyFileByNio(File source, File target) throws IOException {
        long startTime = System.currentTimeMillis();
        if (!target.exists()) {
            target.createNewFile();
        } else {
            target.delete();
        }
        RandomAccessFile read = new RandomAccessFile(source, "rw");
        RandomAccessFile write = new RandomAccessFile(target, "rw");

        // 管道
        FileChannel readChannel = read.getChannel();
        FileChannel writeChannel = write.getChannel();

        // 缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
        while (readChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            writeChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        writeChannel.close();
        read.close();
        long endTime = System.currentTimeMillis();
        long timeWaste = endTime - startTime;
        System.out.println("NIO 耗时:" + (timeWaste));
        return timeWaste;
    }

    public static void main(String[] args) throws IOException {
        String sourcePath = "/Users/haohao/Desktop/实现领域驱动设计.pdf";
        String targetIoPath = "/Users/haohao/Desktop/io.zip";
        String targetNioPath = "/Users/haohao/Desktop/nio.zip";
        long timeIoSum = 0L;
        long timeNioSum = 0L;
        for (int i = 0; i <10; i++) {
            timeIoSum += copyFileByIo(new File(sourcePath), new File(targetIoPath));
        }

        for (int i = 0; i < 10; i++) {
            timeNioSum += copyFileByNio(new File(sourcePath), new File(targetNioPath));
        }
        System.out.println("io Sum:"+timeIoSum);
        System.out.println("nio sum:" + timeNioSum);
    }


}
