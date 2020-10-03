package com.jayden.design.pattern.singleton.lazy;

import com.jayden.design.pattern.singleton.register.EnumSingleton;

/**
 * <p>
 * 懒汉式单例或者叫延迟加载单例
 * 1、优点：节省内存，避免资源浪费，简单易读,线程安全
 * 2、缺点：海量高并发的情况下，会有严重的性能问题
 * </p>
 *
 * @author wangwei Created on 2020-02-27 16:23:11
 * @version 1.0
 */
public class LazySynchronizedMethodSingleton {

    // 类被加载的时候，单例对象就已经声明
    private static LazySynchronizedMethodSingleton instance;

    // 构造方法私有化
    private LazySynchronizedMethodSingleton() {

    }

    // 提供一个全局的访问点
    public synchronized final static LazySynchronizedMethodSingleton getInstance() {
        if (null == instance) {
            instance = new LazySynchronizedMethodSingleton();
        }
        return instance;
    }

}
