package com.spring.basic.ex04;

import javax.annotation.Resource;

public class Book {
	@Resource(name = "paper2")	
	private Paper paper;

	Book () {
		
	}
	

	Book (Paper paper) {
		this.paper = paper;
	}
	public Paper getPaper() {
		return paper;
	}


//	@Autowired
//	@Qualifier("paper2")
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	
	

}
