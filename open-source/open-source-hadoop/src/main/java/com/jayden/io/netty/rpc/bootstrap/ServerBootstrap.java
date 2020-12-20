package com.jayden.io.netty.rpc.bootstrap;

import com.jayden.io.netty.rpc.server.RpcServer;

public class ServerBootstrap {

    public static void main(String[] args) throws Exception {
        new RpcServer("localhost", 8080).start();
    }
}
