package com.hh.base.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author HaoHao
 * @date 2020/5/11 5:58 下午
 */
public class Client {

    private Selector selector;


    public Client(String ip, int port) throws IOException {
        System.out.println("client init,ip:" + ip + ",port:" + port);
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        this.selector = Selector.open();
        // todo ?
        channel.connect(new InetSocketAddress(ip, port));
        // 注册到selector
        channel.register(selector, SelectionKey.OP_CONNECT);

    }

    public void listen() throws IOException, InterruptedException {
        System.out.println("client listen start~");
        while (true) {
            System.out.println("client select return " + selector.select());
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                if (selectionKey.isConnectable()) {
                    System.out.println("client OP_CONNECT");
                    // OP_CONNECT
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    // todo?
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    // 向客户端发送消息
                    channel.write(ByteBuffer.wrap("客户端请求连接服务端".getBytes(StandardCharsets.UTF_8)));
                    // 连接成功后,注册读事件
                    channel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()){
                    // OP_READ
                    System.out.println("client OP_READ");
                    // 可读通道
                    SocketChannel readChannel = (SocketChannel) selectionKey.channel();
                    // buffer
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1 << 8);
                    // 读数据到buffer 中
                    readChannel.read(byteBuffer);
                    byteBuffer.flip();
                    System.out.println("client 收到服务端端消息:" + new String(byteBuffer.array(),StandardCharsets.UTF_8).trim());
                    // 回调客户端
                    readChannel.write(ByteBuffer.wrap("客户端已经收到消息".getBytes(StandardCharsets.UTF_8)));
                    Thread.sleep(2000);
                }
            }

        }
    }
}
