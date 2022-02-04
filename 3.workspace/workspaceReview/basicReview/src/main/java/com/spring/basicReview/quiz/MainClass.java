package com.spring.basicReview.quiz;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		GenericXmlApplicationContext ct =
				new GenericXmlApplicationContext("classpath:quiz-config.xml");
		Computer computer = ct.getBean("computer", Computer.class);
		computer.computerInfo();
	}
}
