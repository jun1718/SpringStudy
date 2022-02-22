package com.spring.mvc.board.service;

import java.util.List;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.commons.SearchVO;

public interface IBoardService {
		// 게시글 등록 기능
		void insert(BoardVO article);
		
		//# 검색, 페이징 기능이 포함된 게시물 목록조회기능
		List<BoardVO> getArticleList(SearchVO search);
		
		Integer countArticles(SearchVO search);
		
	
		
		/*
		// 게시글 목록 조회
		List<BoardVO> getArticleList();
		
		//게시글 페이징 목록조회기능
		List<BoardVO> getArticleListPaging(PageVO paging);
		
		//제목으로 검색기능(페이징)
		List<BoardVO> getArticleListByTitle(SearchVO search);
		
		//제목으로 검색 이후 게시물 수 조회기능
		Integer countArticleByTitle(SearchVO search);
		
		//작성자로 검색기능
		List<BoardVO> getArticleListByWriter(SearchVO search);
		
		//작성자로 검색 이후 게시물 수 조회기능
		Integer countArticleByWriter(SearchVO search);
		
		//내용으로 검색
		List<BoardVO> getArticleListByContent(SearchVO search);
		
		//내용으로 검색이후 게시물수조회기능
		Integer countArticleByContent(SearchVO search);
		
		//제목+내용으로검색
		List<BoardVO> getArticleListByTitleContent(SearchVO search);
		
		//제목+내용으로 검색이후 게시물 수 조회기능
		Integer countArticleByTitleContent(SearchVO search);
		
		// 총 게시물의 수 조회기능
		Integer countArticles();
		*/
		
		// 게시글 상세 조회기능
		BoardVO getArticle(Integer boardNo);
		
		// 게시글 수정 기능
		void update(BoardVO article);
		
		// 게시글 삭제 기능
		void delete(Integer boardNo);
}
