package com.spring.mvc.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.repository.IBoardMapper;
import com.spring.mvc.commons.SearchVO;

@Service
public class BoardService implements IBoardService {

	@Inject
	private IBoardMapper mapper;
	
	@Override
	public void insert(BoardVO article) {
		// TODO Auto-generated method stub
		mapper.insert(article);
	}

	@Override
	public List<BoardVO> getArticleList(SearchVO search) {
		// TODO Auto-generated method stub
		List<BoardVO> list = mapper.getArticleList(search); 
		
		for (BoardVO article : list) {
			long nowTime = System.currentTimeMillis();
			long regTime = article.getRegDate().getTime();
			
			if (nowTime - regTime <= 60 * 60 * 24 * 5 *1000) {
				article.setNewMark(true);
			}
		}
		
		return list;
	}
	
	@Override
	public Integer countArticles(SearchVO search) {
		// TODO Auto-generated method stub
		return mapper.countArticles(search);
	}
	
	/*
	@Override
	public List<BoardVO> getArticleListByTitle(SearchVO search) {
		// TODO Auto-generated method stub
		return mapper.getArticleListByTitle(search);
	}

	@Override
	public Integer countArticleByTitle(SearchVO search) {
		// TODO Auto-generated method stub
		return mapper.countArticleByTitle(search);
	}

	@Override
	public List<BoardVO> getArticleListByWriter(SearchVO search) {
		// TODO Auto-generated method stub
		return mapper.getArticleListByWriter(search);
	}

	@Override
	public Integer countArticleByWriter(SearchVO search) {
		// TODO Auto-generated method stub
		return mapper.countArticleByWriter(search);
	}

	@Override
	public List<BoardVO> getArticleListByContent(SearchVO search) {
		// TODO Auto-generated method stub
		return mapper.getArticleListByContent(search);
	}

	@Override
	public Integer countArticleByContent(SearchVO search) {
		// TODO Auto-generated method stub
		return mapper.countArticleByContent(search);
	}

	@Override
	public List<BoardVO> getArticleListByTitleContent(SearchVO search) {
		// TODO Auto-generated method stub
		return mapper.getArticleListByTitleContent(search);
	}

	@Override
	public Integer countArticleByTitleContent(SearchVO search) {
		// TODO Auto-generated method stub
		return mapper.countArticleByTitleContent(search);
	}

	@Override
	public List<BoardVO> getArticleListPaging(PageVO paging) {
		// TODO Auto-generated method stub
		System.out.println("BoardService test paging : " + paging);
		return mapper.getArticleListPaging(paging);
	}
	
	@Override
	public Integer getCountArticles() {
		// TODO Auto-generated method stub
		return mapper.getCountArticles();
	}

	*/
	@Override
	public BoardVO getArticle(Integer boardNo) {
		// TODO Auto-generated method stub
		mapper.updateViewCnt(boardNo);
		return mapper.getArticle(boardNo);
	}
	
	

	@Override
	public void update(BoardVO article) {
		// TODO Auto-generated method stub
		mapper.update(article);
	}

	@Override
	public void delete(Integer boardNo) {
		// TODO Auto-generated method stub
		mapper.delete(boardNo);
	}

}
