package com.spring.basic.ex01;

public class Restaurant {
	private Chef chef;
	
	public Restaurant(Chef chef) {
		// TODO Auto-generated constructor stub
		System.out.println("레스토랑 생성!");
		this.chef = chef;
	}
	
	public void orderDinner() {
		System.out.println("저녁메뉴를 주문받습니다.");
		chef.cook();
	}
}
