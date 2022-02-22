package com.spring.mvc.board.test;

import org.junit.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class UriComponentsTest {
	@Test
	public void uriTest() {
		UriComponents ucp = UriComponentsBuilder.newInstance()
							.path("/board/list")
							.queryParam("page", 4)
							.queryParam("countPerPage", 10)
							.queryParam("keyword", "사랑해")
							.queryParam("condition", "title")
							.build();
		
		System.out.println("uri : " + ucp.toUriString());
		
	}
}
