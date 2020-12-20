package com.jayden.io.netty.rpc.consumer;

import com.jayden.io.netty.rpc.api.IRpcService;
import com.jayden.io.netty.rpc.client.RpcClient;

public class RpcConsumer {

    public static void main(String[] args) {
        IRpcService service = RpcClient.create(IRpcService.class);
        System.out.println(service.hello("Tom老师"));
        System.out.println("8 + 2 = " + service.add(8, 2));
        System.out.println("8 - 2 = " + service.sub(8, 2));
        System.out.println("8 * 2 = " + service.multiply(8, 2));
        System.out.println("8 / 2 = " + service.div(8, 2));
    }

}
