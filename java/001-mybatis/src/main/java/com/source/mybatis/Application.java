package com.source.mybatis;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.source.mybatis.com.souces.beans.Dept;

public class Application {
	public static void main(String[] args) throws Exception {
		float[][] f1 = { { 1.2f, 2.3f }, { 4.5f, 5.6f } };
		Object oo = f1;
		f1[1] = oo;
		System.out.println("Best Wishes" + f1[1]);
	}



	private static void testJdbc() throws Exception {
		Class.forName("com.mysql.cj.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crowd");
		PreparedStatement pSatement = con.prepareStatement("INSERT INTO dept values(?,?,?)");
		pSatement.setInt(1, 1009);
		pSatement.setString(2, "test");
		pSatement.setString(3, "test");
		try {
			pSatement.executeUpdate();
			con.commit();
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		} finally {
			if (pSatement != null) {
				pSatement.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}



	private static void testMybatis() throws Exception {
		Dept dept = new Dept();
		dept.setdName("金融事业部");
		dept.setLoc("重庆");
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		session.insert("insertDept", dept);
		session.commit();
		session.close();
	}
}
