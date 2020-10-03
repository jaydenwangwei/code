package com.jayden.design.pattern.singleton.lazy;

/**
 * <p>
 * 懒汉式单例或者叫延迟加载单例
 * 1、优点：节省内存，避免资源浪费，简单易读；
 * 2、缺点：线程不安全，如果多个线程同时抢占到getInstance()方法，会导致创建多个对象致使单例失效(也会出现后者覆盖前者)
 * </p>
 *
 * @author wangwei Created on 2020-02-27 16:23:11
 * @version 1.0
 */
public class LazySimpleSingleton {

    // 类被加载的时候，单例对象就已经声明
    private static LazySimpleSingleton instance;

    // 构造方法私有化
    private LazySimpleSingleton() {

    }

    // 提供一个全局的访问点
    public final static LazySimpleSingleton getInstance() {
        if (null == instance) {
            instance = new LazySimpleSingleton();
        }
        return instance;
    }
}
