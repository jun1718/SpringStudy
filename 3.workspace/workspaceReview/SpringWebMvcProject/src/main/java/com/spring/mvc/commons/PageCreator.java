package com.spring.mvc.commons;

public class PageCreator {
	private PageVO paging;
	private Integer countArticles;
	
	private Integer beginPage;
	private Integer endPage;
	private boolean prev;
	private boolean next;
	
	private final int displayPageNum = 10;
	
	public PageCreator(Integer countArticles, PageVO paging) {
		// TODO Auto-generated constructor stub	
		this.setPaging(paging);
		this.setCountArticles(countArticles);
		calcData();
	}
	
	private void calcData() {
		endPage = (int) Math.ceil(paging.getPage() / (double) displayPageNum) * displayPageNum;
		beginPage = (endPage - displayPageNum) + 1;
		
		prev = (beginPage != 1);
		next = (countArticles > (endPage * paging.getCountPerPage()));
		
		if (!next
				&& countArticles < (endPage * paging.getCountPerPage())) {
			endPage = (int) Math.ceil(countArticles / (double) paging.getCountPerPage());
		}
	}

	public PageVO getPaging() {
		return paging;
	}

	public void setPaging(PageVO paging) {
		this.paging = paging;
	}

	public Integer getCountArticles() {
		return countArticles;
	}

	public void setCountArticles(Integer countArticles) {
		this.countArticles = countArticles;
	}

	public Integer getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(Integer beginPage) {
		this.beginPage = beginPage;
	}

	public Integer getEndPage() {
		return endPage;
	}

	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	@Override
	public String toString() {
		return "PageCreator [paging=" + paging + ", countArticles=" + countArticles + ", beginPage=" + beginPage
				+ ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum
				+ "]";
	}
	
	
}
