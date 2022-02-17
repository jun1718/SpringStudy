package com.spring.mvc.board.repository;

import java.util.List;

import com.spring.mvc.board.model.BoardVO;

public interface IBoardMapper {
	
	public void insert(BoardVO article);
	
	public List<BoardVO> getArticleList();
	
	public BoardVO getArticle(Integer boardNo);
	
	public void update(BoardVO article);
	
	public void delete(Integer boardNo);

}
