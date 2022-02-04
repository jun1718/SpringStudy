package com.spring.basicReview.ex04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Printer {
	@Autowired
	@Qualifier("paper1")
	private Paper paper;
	
	public Printer() {
		
	}
	
//	@Autowired
	public Printer(Paper paper) {
		// TODO Auto-generated constructor stub
		this.paper = paper;
	}
	
//	@Autowired
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	
	
	public void showPaperInfo() {
		for (String info : paper.data) {
			System.out.println(info);
		}
	}
}
