package com.spring.web.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.web.model.BoardVO;

@Repository
public class BoardDAO implements IBoardDAO {
	// DB 대체 자료구조
	List<BoardVO> articles = new ArrayList<>();
	
	@Override
	public List<BoardVO> getArticles() {
		// TODO Auto-generated method stub
		return articles;
	}

	@Override
	public void insertArticle(BoardVO article) {
		// TODO Auto-generated method stub
		articles.add(article);
		System.out.println("게시글 등록 완료!");
	}

	@Override
	public void deleteArticle(int index) {
		// TODO Auto-generated method stub
		articles.remove(index);
	}

	@Override
	public BoardVO getContent(int index) {
		// TODO Auto-generated method stub
		return articles.get(index);
	}

	@Override
	public void modifyArticle(BoardVO article, int index) {
		// TODO Auto-generated method stub
		articles.set(index, article);
	}

}
