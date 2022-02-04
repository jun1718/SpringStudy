package com.spring.basicReview.ex01;

public class Restaurant {
	private Chef chef;
	
	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void orderDinner() {
		System.out.println("요리를 주문받습니다.");
		chef.cook();
	}
}
