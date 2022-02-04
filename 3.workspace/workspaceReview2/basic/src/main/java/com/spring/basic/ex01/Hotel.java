package com.spring.basic.ex01;

public class Hotel {

	private Restaurant res;
	
	public Hotel(Restaurant res) {
		// TODO Auto-generated constructor stub
		System.out.println("hotel 생성!");
		this.res = res;
	}
	
	public void reserveRestaurant() {
		System.out.println("레스토랑을 예약합니다.");
		res.orderDinner();
	}

}
