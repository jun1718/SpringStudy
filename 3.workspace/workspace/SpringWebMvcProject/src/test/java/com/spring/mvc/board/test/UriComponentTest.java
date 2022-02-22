package com.spring.mvc.board.test;

import org.junit.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class UriComponentTest {
	@Test
	public void testUrilComp() {
		//uri를 쉽게 작성할수 있도록 도와주는 유틸 클래스 UriComponentsBuilder 사용하기
		UriComponents ucp = UriComponentsBuilder.newInstance()
							.path("/board/list")
							.queryParam("page", 3)
							.queryParam("countPerPage", 20)
							.queryParam("keyword", "희은")
							.queryParam("condition", "title")
							.build();
		
		System.out.println(ucp.toUriString());
	}
}
