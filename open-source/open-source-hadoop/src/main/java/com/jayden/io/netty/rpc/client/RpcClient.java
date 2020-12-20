package com.jayden.io.netty.rpc.client;

import java.lang.reflect.Proxy;


/**
 * Rpc接口代理类
 */
public class RpcClient {

    /**
     * 声明服务
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T create(Class<?> clazz) {
        T result = (T) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{clazz},
                new ProxyHandler());
        return result;
    }

}



