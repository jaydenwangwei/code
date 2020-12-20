package com.jayden.io.netty.single.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * 客户端的处理类
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * @param ctx
     * @throws Exception 当该NettyClientHandler被初始化的时候，就会调用该channelActive方法一次
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("Hello,Netty Server!");
        System.out.println("客户端已经发送消息");
    }

    /**
     * @param ctx
     * @param msg
     * @throws Exception channelRead 用来处理服务端返回来的数据。
     */
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端收到的消息是: " + msg.toString());
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
