package com.spring.mvc.commons;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchVO extends PageVO {
	private String keyword;
	private String condition;
	
	public SearchVO() {
		// TODO Auto-generated constructor stub
		this.keyword = "";
		this.condition = "";
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "SearchVO [ page = " + this.getPage() + " countPerPage = " + this.getCountPerPage() + "keyword=" + keyword + ", condition=" + condition + "]";
	}
	
	
}
