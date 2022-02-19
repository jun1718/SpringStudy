package com.spring.mvc.board.service;

import java.util.List;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.commons.PageVO;


public interface IBoardService {
	
	public void insert(BoardVO article);
	
	public List<BoardVO> getArticleList();
	
	public List<BoardVO> getArticleListPaging(PageVO paging);
	
	public BoardVO getArticle(Integer boardNo);
	
	public void update(BoardVO article);
	
	public void delete(Integer boardNo);
}
