package com.jayden.design.pattern.factory.factorymethod;

import com.jayden.design.pattern.factory.enyity.ICourse;
import com.jayden.design.pattern.factory.enyity.MethodCourseImpl;

public class MethodFactoryImpl implements IFactory {

    public ICourse iCourse() {
        return new MethodCourseImpl();
    }
}
