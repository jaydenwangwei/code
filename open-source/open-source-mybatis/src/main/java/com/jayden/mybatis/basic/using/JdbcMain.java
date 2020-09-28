package com.jayden.mybatis.basic.using;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * JDBC的原始操作，天龙八步
 */
public class JdbcMain {
    public static void main(String[] args) throws Exception {
        // 第一步：加载JDBC的驱动程序
        Class.forName("com.mysql.jdbc.Driver");

        // 第二步：构建连接
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
        con.setAutoCommit(false);

        // 第三步：构建SQL语句的执行器（Statement或者PreparedStatement）
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO DEPT VALUES(?,?,?)");

        // 第四步：执行SQL叙述
        preparedStatement.setInt(1, 1009);
        preparedStatement.setString(2, "test");
        preparedStatement.setString(3, "test");
        try {
            // 第五步：向数据库交付执行SQL语句并获取执行结果
            int count = preparedStatement.executeUpdate();

            // 第六步：处理执行结果
            System.out.println(count);

            con.commit();
        } catch (Exception ex) {
            con.rollback();
            ex.printStackTrace();
        } finally {
            // 第七步：关闭执行器
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            // 第八步：关闭连接
            if (con != null) {
                con.close();
            }
        }
    }
}
