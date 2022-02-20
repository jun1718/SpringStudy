package com.spring.mvc.board.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.board.repository.IBoardMapper;
import com.spring.mvc.commons.PageVO;

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
		
		countArticles = mapper.getCountArticles();
		System.out.println("게시글 총 갯수 : " + countArticles);
		System.out.println("begin page : " + beginPage);
		System.out.println("end page : " + endPage);
		
		next = (countArticles > endPage * page.getCountPerPage());
		
		if (!next
				&& countArticles < endPage * page.getCountPerPage()) {
			endPage = (int) Math.ceil(countArticles / (double) page.getCountPerPage());
		}
		
		
		System.out.println("prev 활성화 여부 : " + prev);
		System.out.println("next 활성화 여부 : " + next);
		System.out.println("보정된 endPage : " + endPage);
	}
}
