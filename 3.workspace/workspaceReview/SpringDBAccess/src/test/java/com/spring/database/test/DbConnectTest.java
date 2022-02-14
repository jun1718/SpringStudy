package com.spring.database.test;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class DbConnectTest {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/spring2?serverTimezone=Asia/Seoul";
	String uid = "root";
	String upw = "mysql";
	
	@Test
	public void connectTest() {
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, upw);
			
			System.out.println("DB 커넥션 성공!");
			System.out.println("conn : " + conn);
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
