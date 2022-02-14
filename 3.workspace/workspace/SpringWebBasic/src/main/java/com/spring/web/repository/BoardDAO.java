package com.spring.web.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.web.model.BoardVO;

@Repository
public class BoardDAO implements IBoardDAO {
	//게시글을 저장할 리스트: DB대용
	private List<BoardVO> articles = new ArrayList<>();
	
	
	@Override
	public List<BoardVO> getArticles() {
		// TODO Auto-generated method stub
		return articles;
	}

	@Override
	public void insertArticle(BoardVO article) {
		// TODO Auto-generated method stub
		articles.add(article);
		System.out.println(article); // 로그찍기
		System.out.println("게시글 저장 완료!!");
	}

	@Override
	public void deleteArticle(int index) {
		// TODO Auto-generated method stub
		articles.remove(index);
		System.out.println((index + 1) + "번 삭제 완료!");
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
