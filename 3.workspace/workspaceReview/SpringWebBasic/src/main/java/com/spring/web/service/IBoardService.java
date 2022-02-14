package com.spring.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.web.model.BoardVO;

public interface IBoardService {
	// Articles list 반환하는 메서드(전체목록조회 등)
	List<BoardVO> getArticles();
	
	// 게시글 등록을 위한 메서드
	void insertArticle(BoardVO article);
	
	// 게시글 삭제 메서드
	void deleteArticle(int boardNo);
	
	// 게시글 내용(content)을 보기위해 article을 반환하는 메서드 
	BoardVO getContent(int boardNo);
	
	// 새로 작성한 객체를 이용해서 기존특정 번호의 게시글을  수정하기 위한 메서드
	void modifyArticle(BoardVO article, int boardNo);
}
