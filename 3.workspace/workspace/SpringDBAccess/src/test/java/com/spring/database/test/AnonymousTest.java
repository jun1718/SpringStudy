package com.spring.database.test;

public class AnonymousTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car s = new Sonata();
		s.run();
		
		Car ferrari = new Car() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("페라리가 쌩쌩 달립니다.");
			}
		};
		
		ferrari.run();
		
		new Car() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("말리부가 팍팍달립니다.");
			}
		}.run();
		
		//lambda식 적용 : 인터페이스 안에 추상메서드가 단 하나일 경우만 가능!
		Car tucson = () -> {System.out.println("투싼이 달립니다.");};
		tucson.run();
		
		
		//////////////////////////////
		
		
		//계산기 인터페이스와 람다식
		Calculator sharp = new Calculator() {
			
			@Override
			public int add(int n1, int n2) {
				// TODO Auto-generated method stub
				System.out.println("샤프 계산기의 덧셈!");
				return n1 + n2;
			}
		};
		
		System.out.println("=========================");
		System.out.println(sharp.add(10, 15));
		
		Calculator casio = (x, y) -> {
			System.out.println("카시오 계산기의 덧셈!");
			return x + y;
		};
		
		System.out.println(casio.add(100, 200));
		
		// 위치럼 람다조건인상황에서 리턴말고 내부 메서드에서 할게 추가로 없을때 이렇게까지도 줄일수 있음
		/*
		Calculator shaomi = (x, y) -> {
			return x + y;
		};
		*/
		Calculator shaomi = (x, y) -> x + y;
		
		System.out.println("샤오미 결과: " + shaomi.add(30,  50));
	}

}
