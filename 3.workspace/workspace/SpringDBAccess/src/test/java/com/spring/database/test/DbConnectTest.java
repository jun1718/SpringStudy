package com.spring.database.test;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class DbConnectTest {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/spring?serverTimezone=Asia/Seoul";
		//8버전 커넥터에서만 서버시간과 장소를알려줘야함
	private String uid = "root";
	private String upw = "mysql";
	
	//DB연결 테스트
	@Test
	public void connectTest() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, upw);
			System.out.println("DB 커넥션 성공!");
			System.out.println("conn : " + conn);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {}
		}
	}
}
