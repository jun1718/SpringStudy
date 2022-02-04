package com.spring.basic;

import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloSpring {

	public HelloSpring() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ct = 
				new GenericXmlApplicationContext("classpath:test-config.xml");

		SpringTest st = ct.getBean("test", SpringTest.class);

		st.hello();
		ct.close();
	}

}
