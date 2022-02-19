package com.spring.mvc.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.repository.IBoardMapper;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.commons.PageVO;

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
	public BoardVO getArticle(Integer boardNo) {
		// TODO Auto-generated method stub
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
