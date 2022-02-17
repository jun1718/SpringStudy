package com.spring.mvc.board.test;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class DbConnectTest {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/spring2?serverTimezone=Asia/Seoul";
	private String id = "root";
	private String pw = "mysql";
	
	private Connection conn = null;
	
	@Test
	public void connecTest() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id ,pw);
			
			System.out.println("커넥션 객체 생성 성공!!");
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
