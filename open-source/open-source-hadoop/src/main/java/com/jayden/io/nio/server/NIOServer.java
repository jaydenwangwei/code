package com.jayden.io.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * @author wangwei
 * @description 基于NIO的server的简易程序
 * 两个缺点：
 * 1、使用的时候代码量很大
 * 2、编程模型的复杂度很高
 * 解决方案：
 * 1、基于NIO封装的框架，Netty
 * 2、基于AIO进行解决，真正的异步网络通信
 */
public class NIOServer {
    private static final int PORT = 9090;
    private static final int BLOCK_SIZE = 4096;

    private Selector selector;
    private ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK_SIZE);
    private ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK_SIZE);

    //构造函数
    public NIOServer() {
        try {
            // 1. 打开ServerSocketChannel，用于监听客户端的连接
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 2. 绑定监听端口，设置连接为非阻塞模式
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            // 3. 创建reactor线程，创建多路复用器并启动线程
            selector = Selector.open();
            // 4. 将ServerSocketChannel注册到Reactor线程的多路复用器Selector上，监听ACCEPT事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务端:初始化成功.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 监听端口
     */
    private void listener() throws Exception {
        // 5. 多路复用器在线程run方法的无限循环体内轮询准备就绪的key
        while (true) {
            selector.select();
            // 5.1 返回获取选择的键集并循环处理请求的键集
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            if (selectionKeys.isEmpty()) {
                continue;
            }
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = (SelectionKey) iterator.next();
                iterator.remove();
                // 5.2 处理请求的键集
                handlerKey(selectionKey);
            }
            Thread.sleep(4000);
        }
    }


    /**
     * 处理对应的  SelectionKey
     */
    private void handlerKey(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel server;
        SocketChannel client;

        // 测试此键的通道是否已准备好接受新的套接字连接
        if (selectionKey.isAcceptable()) {
            //此键对应的关联通道
            server = (ServerSocketChannel) selectionKey.channel();
            //接受到此通道套接字的连接
            client = server.accept();
            //配置为非阻塞
            client.configureBlocking(false);
            //注册到selector 等待连接
            client.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            client = (SocketChannel) selectionKey.channel();
            //将缓冲区清空，下面读取
            receiveBuffer.clear();
            //将客户端发送来的数据读取到 buffer中
            int count = client.read(receiveBuffer);
            if (count > 0) {
                String receiveMessage = new String(receiveBuffer.array(), 0, count);
                System.out.println("Server:接受客户端的数据:" + receiveMessage);
                client.register(selector, SelectionKey.OP_WRITE);
            }
        } else if (selectionKey.isWritable()) {
            //发送消息buffer 清空
            sendBuffer.clear();
            //返回该键对应的通道
            client = (SocketChannel) selectionKey.channel();
            String sendMessage = "[返回的结果信息]:" + new Random().nextInt(100);
            //向缓冲区中写入数据
            sendBuffer.put(sendMessage.getBytes());
            //put了数据，标志位被改变
            sendBuffer.flip();
            //数据输出到通道
            client.write(sendBuffer);
            System.out.println("服务器向客户端发送数据:" + sendMessage);
            client.register(selector, SelectionKey.OP_READ);
        }

    }

    public static void main(String[] args) {
        try {
            NIOServer nioServer = new NIOServer();
            nioServer.listener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
