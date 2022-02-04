package com.spring.basicReview.ex02;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ct =
				new GenericXmlApplicationContext("classpath:db-config.xml");
//		DataBaseInfo db1 = ct.getBean("db1", DataBaseInfo.class);
//		DataBaseInfo db2 = ct.getBean("db2", DataBaseInfo.class);
		
		
		MemberDAO dao = ct.getBean("dao", MemberDAO.class);
//		dao.setDbInfo(db2);
		dao.showDBInfo();
		
	}

}