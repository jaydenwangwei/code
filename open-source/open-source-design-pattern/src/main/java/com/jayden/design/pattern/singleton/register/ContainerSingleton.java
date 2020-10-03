package com.jayden.design.pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 容器注册式单例，是懒汉式单例
 * 1、优点：天生是线程安全的，高性能，在所有的线程访问以前，先把对象缓存好，放到一个容器内，Spring的做法
 * 2、缺点：增加了程序员的复杂度，一般应用于框架
 * </p>
 *
 * @author wangwei Created on 2020-02-27 16:23:11
 * @version 1.0
 */
public class ContainerSingleton {
    private ContainerSingleton() {

    }

    private static Map<String, Object> ioc = new ConcurrentHashMap<String, Object>();

    public static Object getInstance(String clazzName) {
        synchronized (ioc) {
            if (!ioc.containsKey(clazzName)) {
                Object obj = null;
                try {
                    obj = Class.forName(clazzName);
                    ioc.put(clazzName, obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
            } else {
                return ioc.get(clazzName);
            }
        }
    }

}
