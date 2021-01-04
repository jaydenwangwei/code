package com.jayden.io.netty.single.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import sun.misc.Signal;

import java.util.concurrent.TimeUnit;

/**
 * @author wangwei
 * @description 基于Netty的server的简易程序
 * <p>
 * 1、如何防止Netty意外退出
 */
public class NettyServer {
    private static final int PORT = 9090;

    public NettyServer() {
    }


    /**
     * 启动服务的私有方法实现。
     *
     * @param hostname 主机名称
     * @param port     端口号
     */
    private void listener(String hostname, int port) {
        // 1.用于接受客户端连接的线程工作组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 2.用于对接受客户端连接读写操作的线程工作组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());
        // 3.辅助类。用于帮助我们创建NETTY服务
        ServerBootstrap server = new ServerBootstrap();
        // 4.绑定两个工作线程组
        server.group(bossGroup, workerGroup)
                //5.设置NIO的通道模式
                .channel(NioServerSocketChannel.class)
                //6.设置TCP缓冲区
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 7.设置发送数据的缓存大小
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                // 8.设置接受数据的缓存大小
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                // 9.设置保持连接
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                // 10.初始化绑定服务通道
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        // 此处添加消息编码器
                        pipeline.addLast(new StringEncoder())
                                // 此处添加消息解码器
                                .addLast(new StringDecoder())
                                // 此处添加业务处理Handler
                                .addLast(new NettyServerHandler());
                    }
                });

        try {
            // 11.启动服务
            ChannelFuture channelFuture = server.bind(hostname, port).sync();
            System.out.println("NettyServer已经启动成功");

            // 12.优雅关闭服务和退出
            channelFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                }
            }).sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // bossGroup.shutdownGracefully();
            // workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new NettyServer().listener("localhost", PORT);
    }
}
