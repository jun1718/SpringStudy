package com.spring.basic.ex02;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		DataBaseInfo dbInfo = new DataBaseInfo();
//		dbInfo.setUid("spring1");
//		dbInfo.setUpw("sss111");
//		dbInfo.setUrl("jdbc:mysql://localhost:3306/spring");
		
		//위처럼하면 귀찮고 보안위험있으니 설정으로 해보자
		
		GenericXmlApplicationContext ct =
				new GenericXmlApplicationContext("classpath:db-config.xml");
//		DataBaseInfo db1 = ct.getBean("db1", DataBaseInfo.class);
//		
//		System.out.println("URL : " + db1.getUrl());
//		System.out.println("UID : " + db1.getUid());
//		System.out.println("UPW : " + db1.getUpw());
//		
//	
//		DataBaseInfo db2 = ct.getBean("db2", DataBaseInfo.class);
//		System.out.println("URL : " + db2.getUrl());
//		System.out.println("UID : " + db2.getUid());
//		System.out.println("UPW : " + db2.getUpw());
		
		
//		MemberDAO dao = new MemberDAO();
//		dao.setDbInfo(db1);
//		dao.showDBInfo();
		
		MemberDAO dao = ct.getBean("dao", MemberDAO.class);
		dao.showDBInfo();
		
	}

}
