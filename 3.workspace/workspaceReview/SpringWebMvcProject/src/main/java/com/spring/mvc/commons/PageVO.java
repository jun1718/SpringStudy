package com.spring.mvc.commons;

public class PageVO {
	private Integer page;
	private Integer countPerPage;
	
	public PageVO() {
		// TODO Auto-generated constructor stub
		System.out.println("생성됨 PageVO");
		this.page = 1;
		this.countPerPage = 10;
	}
	
	public Integer getPageStart() {
		int n = (this.page - 1) * this.countPerPage;
//		this.page = (this.page - 1) * this.countPerPage;
		System.out.println("this.page = " + this.page);
		return n;
//		return (this.page - 1) * countPerPage;
	}
	
	public Integer getPage() {
		System.out.println("getPage() 시랳ㅇ됨");
		return page;
	}
	public void setPage(Integer page) {
		if (page <= 0) {
			page = 1;
		}
		this.page = page;
	}
	public Integer getCountPerPage() {
		System.out.println("getCountPerPage() 실행됨");
		return countPerPage;
	}
	public void setCountPerPage(Integer countPerPage) {
		if (countPerPage <= 0 || countPerPage > 50) {
			countPerPage = 10;
		}
		this.countPerPage = countPerPage;
	}

	@Override
	public String toString() {
		return "PageVO [page=" + page + ", countPerPage=" + countPerPage + "]";
	}
	
	
}
