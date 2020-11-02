package com.jayden.design.pattern.factory.factorymethod;

import com.jayden.design.pattern.factory.enyity.ICourse;

/**
 * <p>
 * 超级工厂模式
 * 1、使用场景
 * A、创建对象需要大量重复的代码
 * B、客户端(应用层)不依赖于产品类实力如何被创建、实现等细节
 * C、一个类通过其子类来指定创建哪个对象
 * 2、优点：
 * A、用户只需关心所需产品对应的工厂，无需关心创建细节
 * B、加入新产品符合开闭原则，提高了系统的可扩展性
 * 3、缺点：
 * A、类的个数容易过多，增加了代码结果的复杂度
 * B、增加了系统的抽象性和理解难度
 * </p>
 *
 * @author wangwei Created on 2020-02-27 16:23:11
 * @version 1.0
 */
public interface IFactory {
    ICourse iCourse();
}
