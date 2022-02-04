package com.spring.basic.quiz;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		GenericXmlApplicationContext ct =
				new GenericXmlApplicationContext("classpath:quiz-config.xml");
		Computer com = ct.getBean("computer", Computer.class);
		com.computerInfo();
		
		ct.close();
	}

}
