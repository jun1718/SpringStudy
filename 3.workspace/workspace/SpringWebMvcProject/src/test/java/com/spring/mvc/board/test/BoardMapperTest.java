package com.spring.mvc.board.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.repository.IBoardMapper;

@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/mvc-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)

public class BoardMapperTest {
	@Autowired
	private IBoardMapper mapper;
	
	//게시글 등록 단위 테스트(junit을 사용한 단위 테스트)
	@Test
	public void insertTest() {
		
		for (int i = 1; i <= 320; i++) {
			BoardVO article = new BoardVO();
			article.setTitle("테스트 제목입니다." + i);
			article.setWriter("김테스트" + i);
			article.setContent("테스트 중이니까 말시키지 마시오!" + i);
			mapper.insert(article);			
		}
		
		System.out.println("게시물 등록 성공!");
	}
	
	
	//게시글 목록 조회 테스트
	@Test
	public void getListTest() {
//		List<BoardVO> list = mapper.getArticleList();
//		for (BoardVO vo : list) {
//			System.out.println(vo);
//		}
		
		mapper.getArticleList().forEach(vo -> System.out.println(vo));
	}
	
	//게시글 단일 조회 테스트
	@Test
	public void getArticleTest() {
		System.out.println(mapper.getArticle(4));
	}
	


	
	//게시글 수정 테스트 :
	//vo의 세터를 사용하여 수정 내용(글제목, 글내용)을 입력하고
	//수정을 테스트해보세요.
	
	@Test
	public void updateTest() {
		BoardVO vo = new BoardVO();
		vo.setBoardNo(4);
		vo.setWriter("김테스트5");
		vo.setTitle("테스트제목입니다.5");
		vo.setContent("테스트 중이니까 말시키지 마시오!4");
		
		mapper.update(vo);
		System.out.println("변경 완료!");
//		getArticleTest();
		System.out.println("수정된 후 정보 : " + mapper.getArticle(4));
		
	}
	
	//게시글 삭제 테스트 : 게시글 번호를 통한 삭제를 확인
	@Test
	public void deleteTest() {
		mapper.delete(6);
		BoardVO vo = mapper.getArticle(6);
		if (vo == null) {
			System.out.println("# 게시물이 없습니다!");
		} else {
			System.out.println("# 게시물 정보 : " + vo);
		}
//		getListTest();
		
	}

	
}
