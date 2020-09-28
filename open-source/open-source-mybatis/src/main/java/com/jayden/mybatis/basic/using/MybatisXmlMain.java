package com.jayden.mybatis.basic.using;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;

/**
 * Mybatis基本操作
 */
public class MybatisXmlMain {
    public static void main(String[] args) throws Exception {
        // 第一步：读取配置文件
        InputStream is= Resources.getResourceAsStream("config/mybatis-config.xml");

        // 第二步：根据配置文件生成
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
    }
}
