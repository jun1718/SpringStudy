package com.spring.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RequestController {
	
	@RequestMapping("/test/request/test")
	public String test() {
		System.out.println("/test/request/test를 요청함");
		return "test";
	}
	
	@RequestMapping("/request/req")
	public String req() {
		System.out.println("/request/req 요청됨");
		return "request/req-ex01";
	}

//	@RequestMapping(value = "/request/basic01", method = RequestMethod.GET)
	@GetMapping("/request/basic01")
	public String basicGet() {
		System.out.println("/reqeust/basic01 요청됨 : GET방식");
		return "request/req-ex01";
	}
	
//	@RequestMapping(value = "/request/basic01", method = RequestMethod.POST)
	@PostMapping("/request/basic01")
	public String basicPost() {
		System.out.println("/reqeust/basic01 요청됨 : POST방식");
		return "request/req-ex01";
	}
	
	
	@RequestMapping(value = "/request/param", method = RequestMethod.GET)
	public String ex(HttpServletRequest request) {
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		System.out.println("/request/param 요청");
		System.out.println("name : " + name);
		System.out.println("age : " + age);
		return "";
	}
	
}
