package com.jayden.design.pattern.singleton.lazy;

/**
 * <p>
 * 懒汉式单例或者叫延迟加载单例
 * 1、优点：节省内存，避免资源浪费，线程安全，性能优化
 * 2、缺点：可读性较差，不优雅
 * </p>
 *
 * @author wangwei Created on 2020-02-27 16:23:11
 * @version 1.0
 */
public class LazySynchronizedBodySingleton {

    // 类被加载的时候，单例对象就已经声明
    private static LazySynchronizedBodySingleton instance;

    // 构造方法私有化
    private LazySynchronizedBodySingleton() {

    }

    // 提供一个全局的访问点
    public final static LazySynchronizedBodySingleton getInstance() {
        // 控制线程阻塞
        if (null == instance) {
            synchronized (LazySynchronizedBodySingleton.class) {
                // 控制对象创建
                if (null == instance) {
                    // 可能会出现指令重排的问题，在声明处加volatile关键字，可以解决指令重排
                    instance = new LazySynchronizedBodySingleton();
                }
            }
        }
        return instance;
    }
}
