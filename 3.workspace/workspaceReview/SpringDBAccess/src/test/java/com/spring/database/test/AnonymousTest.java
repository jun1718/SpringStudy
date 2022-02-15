package com.spring.database.test;

public class AnonymousTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car ferrari = new Car() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("페라리가 씽씽 달립니다~");
			}
		};
		ferrari.run();
		
		Car sonata  = () -> {
			System.out.println("소나타가 부우웅 부우웅 달립니다~");
		};
		
		sonata.run();
		
		new Car() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("투산이 촤촤초차촤 달립니다.");
			}
		}.run();
		
		//////////////////////////
		
		
		Calculator sharp = new Calculator() {
			@Override
			public int add(int n1, int n2) {
				// TODO Auto-generated method stub
				return n1 + n2;
			}
		};
		
		Calculator shaomi = (x, y) -> {
			return x + y;
		};
		
		Calculator casio = (x, y) ->  x + y;
		System.out.println(sharp.add(5, 10));
		System.out.println(shaomi.add(5, 12));
		System.out.println(casio.add(5, 15));
	}

}
