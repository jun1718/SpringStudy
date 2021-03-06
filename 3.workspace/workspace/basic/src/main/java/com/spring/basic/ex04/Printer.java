package com.spring.basic.ex04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Printer {
	@Autowired
	@Qualifier("paper1")
	private Paper paper;
	
	public Printer() {
		super();
	}
	
//	@Autowired // ctrl shift o 하면 자동 import 됨
//	@Qualifier("paper1") Qualifier는 생성자는 안됨으로 여기서는 에러난다.
	public Printer(Paper paper) {
		// TODO Auto-generated constructor stub
		this.paper = paper;
	}
	
//	
//	@Autowired // 위의 생성자 혹은 필드, 메소드(setter) 다 가능
//	@Qualifier("paper1")
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	
	
	public void showPaperInfo() {
		for (String info : paper.data) {
			System.out.println(info);
		}
	}
}
