package com.jayden.io.netty.rpc.provider;

import com.jayden.io.netty.rpc.api.IRpcService;

public class RpcServiceImpl implements IRpcService {
    /**
     * 加法
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * 减法
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    /**
     * 乘法
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * 除法
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int div(int a, int b) {
        return a / b;
    }

    /**
     * 示例接口
     *
     * @param name
     * @return
     */
    @Override
    public String hello(String name) {
        return name + " 你好！";
    }
}
