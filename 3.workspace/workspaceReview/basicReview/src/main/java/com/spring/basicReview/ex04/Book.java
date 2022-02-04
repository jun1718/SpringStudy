package com.spring.basicReview.ex04;

import javax.annotation.Resource;

public class Book {
//	@Autowired
//	@Qualifier("paper1")
	
	@Resource(name = "paper1")
	private Paper paper;

	public Paper getPaper() {
		return paper;
	}


	public void setPaper(Paper paper) {
		this.paper = paper;
	}
}
