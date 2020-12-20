package com.jayden.io.aio.server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Executors;

/**
 * @author wangwei
 * @description 基于AIO的server的简易程序
 */
public class AIOServer {
    private static final int PORT = 9090;

    public AIOServer() {
        listen();
    }

    private void listen() {
        try {
            AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.
                    withCachedThreadPool(Executors.newCachedThreadPool(), 1);
            // 1.绑定端口，启动服务
            final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(threadGroup);
            server.bind(new InetSocketAddress(PORT));
            System.out.println("服务已启动，监听端口" + PORT);

            //2.准备接受数据并会回调函数处理数据
            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

                //由操作系统来触发
                //回调有两个状态，成功
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    System.out.println("IO操作成功，开始获取数据");
                    try {
                        buffer.clear();
                        result.read(buffer).get();
                        buffer.flip();
                        result.write(buffer);
                        buffer.flip();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    } finally {
                        try {
                            result.close();
                            server.accept(null, this);
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }

                    System.out.println("操作完成");
                }

                //回调有两个状态，失败
                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("IO操作是失败: " + exc);
                }
            });

            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AIOServer().listen();
    }
}
