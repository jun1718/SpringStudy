package com.spring.basic.ex01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public MainClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GenericXmlApplicationContext ct = 
				new GenericXmlApplicationContext("classpath:test-config.xml");
		Hotel hotel = ct.getBean("hotel", Hotel.class);
		hotel.reserveRestaurant();
	}

}
