package com.spring.mvc.board.service;

import java.util.List;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.commons.SearchVO;


public interface IBoardService {
	
	public void insert(BoardVO article);
	
	public List<BoardVO> getArticleList(SearchVO search);
	
	public Integer countArticles(SearchVO search);
	
	
	/*
	public Integer getCountArticles();
	public List<BoardVO> getArticleListPaging(PageVO paging);
	
	
	public List<BoardVO> getArticleListByTitle(SearchVO search);
	
	public Integer countArticleByTitle(SearchVO search);
	
	public List<BoardVO> getArticleListByWriter(SearchVO search);
	
	public Integer countArticleByWriter(SearchVO search);
	
	public List<BoardVO> getArticleListByContent(SearchVO search);
	
	public Integer countArticleByContent(SearchVO search);
	
	public List<BoardVO> getArticleListByTitleContent(SearchVO search);
	
	public Integer countArticleByTitleContent(SearchVO search);
	*/
	
	public BoardVO getArticle(Integer boardNo);
	
	
	
	public void update(BoardVO article);
	
	public void delete(Integer boardNo);
}
