package com.jayden.io.netty.rpc.protocol;

import java.io.Serializable;

/**
 * 自定义传输业务数据结构
 */
public class InvokerProtocol implements Serializable {

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 形参列表
     */
    private Class<?>[] paras;

    /**
     * 实参列表
     */
    private Object[] values;


    public InvokerProtocol() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParas() {
        return paras;
    }

    public void setParas(Class<?>[] paras) {
        this.paras = paras;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
