package com.jayden.design.pattern.singleton.lazy;

/**
 * <p>
 * 懒汉式单例或者叫延迟加载单例
 * 1、优点：节省内存，避免资源浪费，线程安全，性能优化，可以防止被反射机制破坏
 * 2、缺点：在解决反射机制破坏时代码不优雅
 * </p>
 *
 * @author wangwei Created on 2020-02-27 16:23:11
 * @version 1.0
 */
public class LazySynchronizedInnerClazzSingleton {

    // 构造方法私有化
    private LazySynchronizedInnerClazzSingleton() {
        // 防止被反射破坏
        if (null != LazyHolder.INSTANCE) {
            throw new RuntimeException("非法构造！");
        }
    }

    // 提供一个全局的访问点
    public final static LazySynchronizedInnerClazzSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * 内部类
     */
    private static final class LazyHolder {
        private static final LazySynchronizedInnerClazzSingleton INSTANCE = new LazySynchronizedInnerClazzSingleton();
    }
}
