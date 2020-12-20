package com.jayden.io.netty.single.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author wangwei
 * @description 基于Netty的client的简易程序
 */
public class NettyClient {
    private static final int PORT = 9090;

    public NettyClient() {
    }

    private void listener() {
        // 1.用于连接服务端的线程工作组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        // 2.辅助类。用于帮助我们向Netty发送请求
        Bootstrap client = new Bootstrap();
        // 3.绑定工作线程组
        client.group(workerGroup)
                //5.设置NIO的通道模式
                .channel(NioSocketChannel.class)
                //6.设置TCP缓冲区
                .option(ChannelOption.TCP_NODELAY, true)
                // 7.初始化绑定服务通道
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        // 此处添加消息编码器
                        pipeline.addLast(new StringEncoder())
                                // 此处添加消息解码器
                                .addLast(new StringDecoder())
                                // 此处添加客户端业务处理Handler
                                .addLast(new NettyClientHandler());
                    }
                });

        try {
            // 客户端连接服务端的地址和端口
            client.connect("localhost", PORT).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NettyClient().listener();
    }
}
