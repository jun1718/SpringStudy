package com.spring.mvc.commons;

public class PageVO {
	private Integer page; // 사용자가 요청한 페이지 번호
	private Integer countPerPage; // 한 페이지당 들어갈 게시물의 수
	
	public PageVO() {
		// TODO Auto-generated constructor stub
		this.page = 1;
		this.countPerPage = 10;
	}
	
	public Integer getPageStart() {
		return (this.page - 1) * this.countPerPage;
	}
	
	public Integer getPage() {
		return page;
	}
	
	public void setPage(Integer page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	public Integer getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(Integer countPerPage) {
		if (countPerPage <=0 || countPerPage > 50) {
			this.countPerPage = 10;
			return;
		}
		this.countPerPage = countPerPage;
	}

	@Override
	public String toString() {
		return "PageVO [page=" + page + ", countPerPage=" + countPerPage + "]";
	}
	
	
}
