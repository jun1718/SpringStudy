package com.spring.database.jdbc.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.database.jdbc.board.model.BoardVO;
import com.spring.database.jdbc.board.repository.IBoardDAO;

@Service
public class BoardService implements IBoardService {
	@Autowired
	private IBoardDAO dao;
	
	@Override
	public List<BoardVO> getArticles() {
		// TODO Auto-generated method stub
		return dao.getArticles();
	}

	@Override
	public void insertArticle(BoardVO article) {
		// TODO Auto-generated method stub
		dao.insertArticle(article);
	}

	@Override
	public void deleteArticle(int boardNo) {
		// TODO Auto-generated method stub
		dao.deleteArticle(boardNo);
	}

	@Override
	public BoardVO getContent(int boardNo) {
		// TODO Auto-generated method stub
		return dao.getContent(boardNo);
	}

	@Override
	public void modifyArticle(BoardVO article) {
		// TODO Auto-generated method stub
		dao.modifyArticle(article);
	}

	@Override
	public List<BoardVO> getSearchList(String keyword) {
		// TODO Auto-generated method stub
		keyword = "%" + keyword + "%";
		return dao.getSearchList(keyword);
	}
}
