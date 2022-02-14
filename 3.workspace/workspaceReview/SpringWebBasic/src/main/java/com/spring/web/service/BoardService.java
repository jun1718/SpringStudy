package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.model.BoardVO;
import com.spring.web.repository.IBoardDAO;

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
		dao.deleteArticle(boardNo - 1);
	}

	@Override
	public BoardVO getContent(int boardNo) {
		// TODO Auto-generated method stub
		return dao.getContent(boardNo - 1);
	}

	@Override
	public void modifyArticle(BoardVO article, int boardNo) {
		// TODO Auto-generated method stub
		dao.modifyArticle(article, boardNo - 1);
	}

}
