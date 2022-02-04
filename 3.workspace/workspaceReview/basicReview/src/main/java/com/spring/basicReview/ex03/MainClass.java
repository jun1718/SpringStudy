package com.spring.basicReview.ex03;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ct =
				new GenericXmlApplicationContext("classpath:prototype-config.xml");
		
		Person hong = ct.getBean("person", Person.class);
		Person kim = ct.getBean("person", Person.class);
		
		System.out.println("hong의 주소 : " + hong);
		System.out.println("kim의 주소 : " + kim);
		System.out.println("hong과 kim은 같은 객체인가 ? : " + (hong == kim));
		
		kim.setName("김철수");
		kim.setAge(30);
		
		//kim만 이름나이 바꾸고 싶은데 싱글톤이라서 hong의 이름과 나이도 김철수와 30세가 된다..
		//싱글톤이 아니게해서 아래 문제를 푼뒤 출력결과처럼 나오게 하여라
		
		System.out.println("hong의 이름: " + hong.getName());
		System.out.println("hong의 나이: " + hong.getAge());
		System.out.println("kim의 이름: " + kim.getName());
		System.out.println("kim의 나이: " + kim.getAge());
	}
}
