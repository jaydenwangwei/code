package com.jayden.design.pattern.factory.simplefactory;

import com.jayden.design.pattern.factory.enyity.SimpleCourseImpl;
import com.jayden.design.pattern.factory.enyity.ICourse;
/**
 * <p>
 * 简单工厂模式
 * 1、优点：只需要传一个正确的参数，就可以获取你所需要的对象，不需要知道创建的细节
 * 2、缺点：
 * A、工厂类的职责相对过重，增加新的产品时需要修改工厂类的判断逻辑，违背开闭原则
 * B、不易于扩展过于复杂的产品结构
 * </p>
 *
 * @author wangwei Created on 2020-02-27 16:23:11
 * @version 1.0
 */
public class CourseFactory {
    public ICourse create(String name) {
        if ("java".equals(name)) {
            return new SimpleCourseImpl();
        }
        return null;
    }

    public ICourse createByClazzStr(String name) {
        try {
            if (null == name || "".equals(name)) {
                return null;
            }
            return (ICourse) Class.forName(name).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ICourse createByClazz(Class clazz) {
        try {
            if (null == clazz) {
                return null;
            }
            return (ICourse) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
