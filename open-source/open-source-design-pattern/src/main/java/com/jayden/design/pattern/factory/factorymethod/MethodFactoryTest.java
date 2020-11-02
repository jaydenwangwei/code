package com.jayden.design.pattern.factory.factorymethod;

import com.jayden.design.pattern.factory.enyity.ICourse;

public class MethodFactoryTest {
    public static void main(String[] args) {
        IFactory factory = new SimpleFactoryImpl();
        ICourse iCourse = factory.iCourse();
        iCourse.record();

        IFactory factory1 = new MethodFactoryImpl();
        ICourse iCourse1 = factory1.iCourse();
        iCourse1.record();

    }
}
