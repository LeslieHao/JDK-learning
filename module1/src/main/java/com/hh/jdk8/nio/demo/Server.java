package com.hh.jdk8.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author HaoHao
 * @date 2020/5/11 5:04 下午
 */
public class Server {

    private Selector selector;

    public Server(int port) throws IOException {
        System.out.println("server init,port:" + port);
        // open a channel
        // ServerSocketChannel 用来监听新进来的tcp 连接
        // 并不能传输数据
        // 一个新的连接到达ServerSocketChannel 时,会创建一个新的SocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // non block
        serverSocketChannel.configureBlocking(false);
        // 绑定tcp 端口
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // get a selector
        this.selector = Selector.open();
        /*
         OP_ACCEPT:ServerSocketChannel的有效事件,服务端收到客户端的一个连接请求会触发
         OP_CONNECT:SocketChannel的有效事件 连接就绪事件,表示服务端监听到了客户端的连接
         OP_READ:SocketChannel的有效事件 读就绪事件,表示channel 中有可读的数据
         OP_WRITE:SocketChannel的有效事件 写就绪事件,表示已经可以向通道中写数据了
         */
        // 将channel 注册到selector(OP_ACCEPT 事件)
        // 当该事件到达时, selector.select() 会返回,否则将阻塞
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() throws IOException, InterruptedException {
        System.out.println("server listen start~");
        while (true) {
            // select 事件
            System.out.println("select return :" + selector.select());
            // todo 返回就绪的通道? 还是所有注册的通道?
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 遍历
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    // OP_ACCEPT
                    System.out.println("server OP_ACCEPT");
                    // 客户端连接事件的通道
                    ServerSocketChannel acceptChannel = (ServerSocketChannel) selectionKey.channel();
                    // 和客户端的连接通道
                    SocketChannel channel = acceptChannel.accept();
                    // 设置非阻塞
                    channel.configureBlocking(false);
                    // 给客户端发消息
                    channel.write(ByteBuffer.wrap("服务端收到客户端连接请求".getBytes(StandardCharsets.UTF_8)));
                    // 注册可读时间
                    channel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    // OP_READ
                    System.out.println("server OP_READ");
                    // 可读通道
                    SocketChannel readChannel = (SocketChannel) selectionKey.channel();
                    // buffer
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1 << 10);
                    // 读数据到buffer 中
                    readChannel.read(byteBuffer);
                    System.out.println("server 收到客户端消息:" + new String(byteBuffer.array(), StandardCharsets.UTF_8));
                    // 回调客户端
                    readChannel.write(ByteBuffer.wrap("服务端已经收到消息".getBytes(StandardCharsets.UTF_8)));
                    Thread.sleep(2000);
                }
            }
        }
    }

}
