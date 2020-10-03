package com.jayden.design.pattern.singleton.hungry;

/**
 * <p>
 * 饿汉式单例
 * 1、优点：线程安全，简单易读；在类加载的同时已经创建好一个静态对象，调用时反应速度快
 * 2、缺点：
 * A、单例的数量很大，达到不可控，运行时才知道大小，导致资源占用不可用，例如Spring里面就有很多单例，不适用
 * B、资源效率不高，可能getInstance()永远不会执行到，但执行该类的其他静态方法或者加载了该类（class.forName)，那么这个实例仍然初始化
 * </p>
 *
 * @author wangwei Created on 2020-02-27 16:23:11
 * @version 1.0
 */
public class HungryStaticSingleton {

    // 类被加载的时候，声明单例对象
    private static final HungryStaticSingleton instance;

    // 静态块初始化,可以更加灵活处理
    static {
        instance = new HungryStaticSingleton();
    }

    // 构造方法私有化
    private HungryStaticSingleton() {

    }

    // 提供一个全局的访问点
    public final static HungryStaticSingleton getInstance() {
        return instance;
    }
}
