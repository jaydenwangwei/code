package com.jayden.design.pattern.factory.factorymethod;

import com.jayden.design.pattern.factory.enyity.ICourse;
import com.jayden.design.pattern.factory.enyity.SimpleCourseImpl;

public class SimpleFactoryImpl implements IFactory {
    public ICourse iCourse() {
        return new SimpleCourseImpl();
    }
}
