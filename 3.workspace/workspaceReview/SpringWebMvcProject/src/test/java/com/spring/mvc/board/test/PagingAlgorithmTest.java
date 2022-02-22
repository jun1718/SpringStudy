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
import com.spring.mvc.commons.SearchVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/mvc-config.xml"})

public class PagingAlgorithmTest {
	@Autowired
	private IBoardMapper mapper;
	
	@Test
	public void pagingAlgorithmTest() {
		int beginPage;
		int endPage;
		int countArticles;
		boolean prev;
		boolean next;
		
		final int displayPage = 10;
		PageVO page = new PageVO();
		
		page.setPage(61);
		page.setCountPerPage(10);
		
		endPage = (int) Math.ceil(page.getPage() / (double) displayPage) * displayPage;
		beginPage = endPage - displayPage + 1;
		
		
		prev = (beginPage != 1);
		
//		countArticles = mapper.getCountArticles();
//		System.out.println("게시글 총 갯수 : " + countArticles);
		System.out.println("begin page : " + beginPage);
		System.out.println("end page : " + endPage);
		
//		next = (countArticles > endPage * page.getCountPerPage());
		
//		if (!next
//				&& countArticles < endPage * page.getCountPerPage()) {
//			endPage = (int) Math.ceil(countArticles / (double) page.getCountPerPage());
//		}
		
		
		System.out.println("prev 활성화 여부 : " + prev);
//		System.out.println("next 활성화 여부 : " + next);
		System.out.println("보정된 endPage : " + endPage);
	}
	
	@Test
	public void searchTitleTest() {
		SearchVO search = new SearchVO();
		search.setKeyword("99");
		
//		List<BoardVO> list = mapper.getArticleListByTitle(search);
//		list.forEach(vo -> System.out.println(vo));
		
//		System.out.println("제목으로 검색한 게시글의 수 : " + mapper.countArticleByTitle(search));
		
		
	}
	@Test
	public void searchWriterTest() {
		SearchVO search = new SearchVO();
		search.setKeyword("허희은");
		
//		List<BoardVO> list = mapper.getArticleListByWriter(search);
//		list.forEach(vo -> System.out.println(vo));
		
//		System.out.println("작성자로 검색한 게시글의 수 : " + mapper.countArticleByWriter(search));
		
	}
	
	@Test
	public void searchContentTest() {
		SearchVO search = new SearchVO();
		search.setKeyword("수정한");
		
//		List<BoardVO> list = mapper.getArticleListByContent(search);
//		list.forEach(vo -> System.out.println(vo));
		
//		System.out.println("내용으로 검색한 게시글의 수 : " + mapper.countArticleByContent(search));
		
	}
	
	@Test
	public void searchTitleContentTest() {
		SearchVO search = new SearchVO();
		search.setKeyword("희은이가");
		
//		List<BoardVO> list = mapper.getArticleListByTitleContent(search);
//		list.forEach(vo -> System.out.println(vo));
		
//		System.out.println("제목 + 내용으로 검색한 게시글의 수 : " + mapper.countArticleByTitleContent(search));
		
	}
	
	@Test
	public void searchTest() {
		SearchVO search = new SearchVO();
		search.setCondition("titleContent");
		search.setKeyword("보");
		
		System.out.println("search : " + search);
		List<BoardVO> list = mapper.getArticleList(search);
		
		list.forEach(vo -> System.out.println(vo));
		
		System.out.println("해당 조건 게시글 수 : " + mapper.countArticles(search));
	}
}
