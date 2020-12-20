package com.jayden.io.netty.single.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 服务处理类实现
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) throws Exception {
        // 处理参数
        String receiveMsg = "服务端收到的消息为: [" + msg.toString() + "]";
        System.out.println(receiveMsg);
        // 返回业务处理结果
        context.writeAndFlush(receiveMsg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) throws Exception {
        cause.printStackTrace();
        context.close();
    }
}
