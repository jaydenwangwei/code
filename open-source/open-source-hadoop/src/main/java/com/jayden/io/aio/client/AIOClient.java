package com.jayden.io.aio.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author wangwei
 * @description 基于AIO的Client的简易程序
 */
public class AIOClient {
    private final AsynchronousSocketChannel client;

    public AIOClient() throws Exception {
        // 1. 初始化客户端
        client = AsynchronousSocketChannel.open();
    }

    public void connect(String host, int port) throws Exception {
        try {
            // 2. 客户端连接服务端并调用回调函数决定是否发送数据
            client.connect(new InetSocketAddress(host, port), null, new CompletionHandler<Void, Void>() {
                @Override
                public void completed(Void result, Void attachment) {
                    try {
                        client.write(ByteBuffer.wrap("这是一条测试数据".getBytes())).get();
                        System.out.println("已发送至服务器");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    exc.printStackTrace();
                }
            });

            // 3. 客户端读物服务端返回的响应数据并调用回调函数决定是否处理返回数据
            final ByteBuffer bb = ByteBuffer.allocate(1024);
            client.read(bb, null, new CompletionHandler<Integer, Object>() {

                        @Override
                        public void completed(Integer result, Object attachment) {
                            System.out.println("IO操作完成" + result);
                            System.out.println("获取反馈结果" + new String(bb.array()));
                        }

                        @Override
                        public void failed(Throwable exc, Object attachment) {
                            exc.printStackTrace();
                        }
                    }
            );

            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }

    }

    public static void main(String args[]) throws Exception {
        new AIOClient().connect("localhost", 8000);
    }
}