
package com.spring.mvc.board.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.board.repository.IBoardMapper;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.commons.SearchVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/mvc-config.xml"})

public class PagingAlgorithmTest {
	/*
	 	# 페이징 알고리즘 만들기
	 	* 사용자가 보게 될 페이지 화면
	  		-한 화면에 페이지를 10개식 끊어서 보여준다면?
	  		ex)1 2 3 4 ... 9 10 [다음] // [이전] 31 32 33 ... 39 40[다음]
	  		
	  			-만약에 총 게시물의 수가 67개라면? 이전, 다음버튼 두개다 없어야함
	  			ex) 1 2 3 4 5 6 7
	  		
	  			-총 게시물 수가 142개이고 현재 12페이지에 사용자가 머물러 있다면? 다음은 없다
	  			ex) [이전] 11 12 13 14 15
	  	
	  	* 위 화면을 구성하는 알고리즘 방법
	  	1. 우선 총 게시물의 수를 조회해야한다.
	  		-총 게시물 수는 DB로부터 수를 조회하는 SQL을 작성
	  	2. 사용자가 현재 위치한 페이지를 기준으로 
	  	끝 페이지 번호를 계산하는 로직 작성
	  		ex)만약 현재 사용자가 보고 있는 페이지가 3페이지고 한 화면에 보여줄 페이지가 10페이지씩이라면
	  			-> 끝페이지 번호는? 10page
	  		      만약 현재 페이지가 37페이지고 한 화면에 보여줄 페이지가 20페이지식이라면 
	  		   	-> 끝페이지 번호는 ?? 40page
	  		   
	  		-끝페이지번호공식: 
	  			Math.ceil(현재위치한 페이지번호 / 한 화면당 보여질 페이지의 수) * 한 화면당 보여질 페이지수
	  			Math.ceil :올림함수
	  	
	   3.시작페이지 번호 계산하기
	   		-현재 위치한 페이지가 15페이지이고, 한 화면에 보여줄 페이지가 10페이지씩이라면??
	   			-> 시작페이지번호는? 11페이지
	   		-현재 위치한 페이지가 73페이지이고, 한 화면에 보여줄 페이지가 20페이지씩이라면??
	   			-> 시작페이지번호는? 61페이지
	   		-공식:
	   			(끝페이지 번호 - 한 화면에 보여줄 페이지의 수) + 1;
	   4. 끝 페이지  보정
	   		- 총 게시물 수가 324개이고 한 페이지당 10개의 게시물을 보여준다
	   		- 그리고 현재 이사람은 31페이지를 보고 있다.
	   		- 그리고 한 화면당 10개씩의 페이지를 보여준다.
	   		- 그렇다면 위 공식에 의한 끝페이지는 어덯게 계산되는가?
				-> 40page
			- 하지만 실제 끝페이지는 몇번이어야 하는가?? 33페이지 
			
			4-1. 이전버튼 활성 여부 설정
				-시작페이지가 1인 화면을 제외한 모든 화면에서 이전이 있어야함
				-공식: 시작페이지 번호가 1로 구해진 시점에서 비활성처리. 나머지는 활성
			4-2. 다음버튼 활성 여부 설정
				-언제 다음버튼을 비활성화 할 것인가??
					-공식:
						보정전 끝페이지번호 x 한 페이지에 들어갈 게시물 수 >= 총게시물수 -> 비활성
						
			4-3. 끝페이지 값 보정
			-다음 버튼이 비활성화 되었을때 총 게시물수에 맞춰 끝페이지 번호를 재보정한다.
			-공식: Math.ceil(총게시물의 수 / 한 페이지에 보여줄 게시물 수)
			
			 
	 */
	
	@Autowired
	private IBoardMapper mapper;
	
	@Test
	public void pagingAlgolithmTest() {
		// 1.총 게시물 수 구하는 테스트
		System.out.println("=============================");
		System.out.println(" # 총 게시물 수 : " + mapper.countArticles());
		System.out.println("=============================");
		
		
		// 2.끝페이지 번호 계산 테스트
		PageVO paging = new PageVO();
		
		paging.setPage(32);
		paging.setCountPerPage(10); // 기본값이 10 되어있어서 사실 setter 안넣어줘도됨
		int displayPage = 20;
		
		int endPage = (int) (Math.ceil(paging.getPage() / (double) displayPage)) * displayPage;
//		System.out.println(Math.ceil(30.0)); // 끝자리가 0 즉 0으로 나누어떨어진 경우엔 올림하지않는다.
		System.out.println("끝페이지지 번호: " + endPage); // 90 나와야함
		
		// 3.시작페이지 번호 계산 테스트
		int beginPage = (endPage - displayPage) + 1;
		
		
		System.out.println("시작페이지 번호 : " + beginPage);
		
		boolean isPrev = (beginPage == 1) ? false : true;
		// 도대체 왜 위에처럼 굳이 삼항쓰는거지
		System.out.println("이전버튼 활성화 여부 : " + isPrev); 
//		System.out.println("isperv : " + !(beginPage == 1));
//		System.out.println("isperv : " + (beginPage != 1));
		
		
		boolean isNext = (mapper.countArticles() <= (endPage * paging.getCountPerPage())) ? false : true;
		
		System.out.println("다음버튼 활성화 여부 : " + isNext);
		
		
		// 끝페이지 보정
		if (!isNext) {
			endPage = (int) Math.ceil(mapper.countArticles() / (double) paging.getCountPerPage());
		}
		
		System.out.println("보정 후 끝페이지 번호 : " + endPage);
		System.out.println("=============================");
		
		
	}
	
	@Test
	public void searchTest() {
		SearchVO search = new SearchVO();
		search.setPage(3);
		search.setCountPerPage(20);

		search.setKeyword("9");
		
		System.out.println("=======================");
		mapper.getArticleListByTitle(search).forEach(vo -> System.out.println(vo));
		System.out.println("=======================");
		
	}
}