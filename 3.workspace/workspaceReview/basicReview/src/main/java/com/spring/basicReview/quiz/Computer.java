package com.spring.basicReview.quiz;

import org.springframework.beans.factory.annotation.Autowired;

public class Computer {
	@Autowired
	private Moniter moniter;
	
	@Autowired
	private Keyboard keyboard;
	
	@Autowired
	private Mouse mouse;
	
	public void computerInfo() {
		System.out.println("*** 컴퓨터 정보 ***");
		moniter.info();
		keyboard.info();
		mouse.info();
	}
	
}
