package com.spring.mvc.board.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.repository.IBoardMapper;
import com.spring.mvc.commons.SearchVO;

@Service
public class BoardService implements IBoardService {

//	@Autowired 둘다 같다 inject는 자바에서 만든거
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
			
			//1일 이내 신규글 new마크 처리 로직
			for (BoardVO article : list) {
				//현재 시간 읽어오기
				long now = System.currentTimeMillis(); // 밀리초로 읽기 (15억 몇초  * 1000초) = 밀리초 
															//이런식으로 
				Date regDate = article.getRegDate();
				long regTime = regDate.getTime(); // 밀리초로 바꿔줌
				
				if (now - regTime < 60 * 60 * 24 * 5 * 1000) { //1은 하루를 의미 2,3,4 이틀삼일사일
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
	public List<BoardVO> getArticleList() {
		// TODO Auto-generated method stub
		return mapper.getArticleList();
	}

	@Override
		public List<BoardVO> getArticleListPaging(PageVO paging) {
			// TODO Auto-generated method stub
			//page = (page - 1) * 10; //객체에서 수행하도록
			
			return mapper.getArticleListPaging(paging);
		}
	
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
		public Integer countArticles() {
			// TODO Auto-generated method stub
			return mapper.countArticles();
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
