package com.spring.web.model;

public class BoardVO {
	private String writer;
	private String content;
	private String title;
	
	public BoardVO() {
		// TODO Auto-generated constructor stub
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "BoardVO [writer=" + writer + ", content=" + content + ", title=" + title + "]";
	}
	
	
	
	
}
