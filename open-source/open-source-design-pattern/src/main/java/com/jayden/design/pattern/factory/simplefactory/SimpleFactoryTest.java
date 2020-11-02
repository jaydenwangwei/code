package com.jayden.design.pattern.factory.simplefactory;

import com.jayden.design.pattern.factory.enyity.ICourse;
import com.jayden.design.pattern.factory.enyity.SimpleCourseImpl;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        CourseFactory factory = new CourseFactory();
        ICourse iCourse = factory.create("java");
        iCourse.record();

        ICourse iCourse1 = factory.createByClazzStr("com.jayden.design.pattern.factory.enyity.CourseImpl");
        iCourse1.record();

        ICourse iCourse2 = factory.createByClazz(SimpleCourseImpl.class);
        iCourse2.record();
    }
}
