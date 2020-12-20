package com.jayden.io.netty.rpc.api;

/**
 * 接口
 */
public interface IRpcService {

    /**
     * 加法
     *
     * @param a
     * @param b
     * @return
     */
    int add(int a, int b);

    /**
     * 减法
     *
     * @param a
     * @param b
     * @return
     */
    int sub(int a, int b);

    /**
     * 乘法
     *
     * @param a
     * @param b
     * @return
     */
    int multiply(int a, int b);

    /**
     * 除法
     *
     * @param a
     * @param b
     * @return
     */
    int div(int a, int b);

    /**
     * 示例接口
     *
     * @param name
     * @return
     */
    String hello(String name);

}
