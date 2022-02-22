package com.spring.mvc.board.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.repository.IBoardMapper;
import com.spring.mvc.commons.PageVO;

//@ContextConfiguration(location = "")


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/mvc-config.xml"})

public class BoardMapperTest {
	@Autowired
	private IBoardMapper mapper;
	
	@Test
	public void insert() {
			BoardVO article = new BoardVO();
			article.setWriter("김깡똥");
			article.setTitle("깡똥이의 제목");
			article.setContent("깡통이의 내용");
			
			mapper.insert(article);
		
//		for (int i = 2; i <= 320; i++) {
//			BoardVO article = new BoardVO();
//			article.setWriter("김깡똥" + i);
//			article.setTitle("깡똥이의 제목" + i);
//			article.setContent("깡통이의 내용" + i);
//			
//			mapper.insert(article);
//		}
		
		System.out.println("정보입력완료!");
		
		
	}
	
	@Test
	public void getArticleListTest() {
//		List<BoardVO> list = mapper.getArticleList();
		
//		for (BoardVO vo : list) {
//			System.out.println(vo);
//		}
		
//		list.forEach(vo -> System.out.println(vo));
	}
	
	@Test
	public void getArticleListPagingTest() {
		PageVO paging = new PageVO();
		
//		List<BoardVO> list = mapper.getArticleListPaging(paging);

	}
	
	@Test
	public void getArticleTest() {
		BoardVO article = mapper.getArticle(5);
		
		
		if (article == null) {
			System.out.println("해당 번호는 없습니다.");
		} else {
			
			System.out.println(article);
		}
	}
	
	@Test
	public void updateTest() {
		System.out.println("수정 전의 정보 : " + mapper.getArticle(6));
		
		BoardVO article = new BoardVO();
		article.setBoardNo(6);
		article.setTitle("수정한 제목");
		article.setContent("수정한 내용");
		article.setWriter("수정한 이름");
		
		mapper.update(article);
		
		System.out.println("수정된 후의 정보 : " + mapper.getArticle(6));
	}
	
	@Test
	public void deleteTest() {
		mapper.delete(1);
		BoardVO article = mapper.getArticle(1);
		if (article == null) {
			System.out.println("해당 번호는 없습니다.");
		} else {
			System.out.println(article);
		}
		
	}
}
