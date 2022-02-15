package com.spring.database.jdbc.board.model;

public class BoardVO {
	private Integer boardNo;
	private String writer;
	private String content;
	private String title;
	
	public BoardVO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Integer getBoardNo() {
		return boardNo;
	}



	public void setBoardNo(Integer boardNo) {
		this.boardNo = boardNo;
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
