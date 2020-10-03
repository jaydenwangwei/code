package com.jayden.design.pattern.singleton.register;

/**
 * <p>
 * 枚举注册式单例，本质是饿汉式单例
 * 1、优点：天生是线程安全的，代码优雅、简单、可以不被反射破坏
 * 2、缺点：单例内的字段数量不能很大，如果达到不可控，导致资源占用不可用
 * </p>
 *
 * @author wangwei Created on 2020-02-27 16:23:11
 * @version 1.0
 */
public enum EnumSingleton {
    INSTANCE;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
