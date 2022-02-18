package com.spring.mvc.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.mvc.board.model.BoardVO;


public interface IBoardService {
	
	public void insert(BoardVO article);
	
	public List<BoardVO> getArticleList();
	
	public BoardVO getArticle(Integer boardNo);
	
	public void update(BoardVO article);
	
	public void delete(Integer boardNo);
}
