package com.spring.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.web.model.UserVO;
import com.spring.web.model.UserVO2;

@Controller
@RequestMapping("/request")
public class RequestController {
	
	@RequestMapping("/test/request/test")
	public String test() {
		System.out.println("/test/request/test를 요청함");
		return "test";
	}
	
	@RequestMapping("/req")
	public String req() {
		System.out.println("/request/req 요청됨");
		return "request/req-ex01";
	}

//	@RequestMapping(value = "/request/basic01", method = RequestMethod.GET)
	@GetMapping("/basic01")
	public String basicGet() {
		System.out.println("/reqeust/basic01 요청됨 : GET방식");
		return "request/req-ex01";
	}
	
//	@RequestMapping(value = "/request/basic01", method = RequestMethod.POST)
	@PostMapping("/basic01")
	public String basicPost() {
		System.out.println("/reqeust/basic01 요청됨 : POST방식");
		return "request/req-ex01";
	}
	
	
	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public String ex(HttpServletRequest request) {
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		System.out.println("/request/param 요청");
		System.out.println("name : " + name);
		System.out.println("age : " + age);
		return "";
	}
	
	@RequestMapping(value = "/req-ex02", method = RequestMethod.GET)
	public void req1() {
		System.out.println("/request/req-ex01 요청됨 : GET");
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void register() {
		System.out.println("/request/join 호출됨 : GET");
	}
	
	/*
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public void register(HttpServletRequest request) {
		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");
		String[] hobby = request.getParameterValues("hobby");
		
		System.out.println("ID : " + id);
		System.out.println("PW : " + pw);
		System.out.println("HOBBY : " + Arrays.toString(hobby));
	}
	*/
	
//	@RequestMapping(value = "/join", method = RequestMethod.POST) 
//	public void register(@RequestParam("userId") String id,
//							@RequestParam("userPw") String pw,
//							@RequestParam(value = "hobby", required = false, defaultValue = "no hobby person") List<String> hobby) {
//		System.out.println("ID : " + id);
//		System.out.println("PW : " + pw);
//		System.out.println("HOBBY : " + hobby.toString());
// 
//	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public void register(UserVO user) {
		System.out.println("Name : " + user.getUserName());
		System.out.println("ID : " + user.getUserId());
		System.out.println("PW : " + user.getUserPw());
		if (user.getHobby() == null) {
			System.out.println("HOBBY : no hobby person");			
		} else {
			System.out.println("HOBBY : " + user.getHobby().toString());			
		}
	}
	
	
	@RequestMapping(value = "/quiz", method = RequestMethod.GET)
	public String quiz() {
		System.out.println("/request/quiz 요청됨 : GET");
		return "request/req-quiz";
	}
	
//	@RequestMapping(value = "/quiz", method = RequestMethod.POST)
//	public String quiz(HttpServletRequest request) {
//		String id = request.getParameter("userId");
//		String pw = request.getParameter("userPw");
//		
//		if (id.equals("abc1234") && pw.equals("xxx4321")) {			
//			return "request/login-success";
//		}
//		
//		return "request/login-fail";
//	}
	
//	@RequestMapping(value = "/quiz", method = RequestMethod.POST)
//	public String quiz(@RequestParam("userId") String id,
//						@RequestParam("userPw") String pw) {
//		if (id.equals("abc1234") && pw.equals("xxx4321")) {
//			return "request/login-success";			
//		}
//		return "request/login-fail";
//	}
	
	@RequestMapping(value = "/quiz", method = RequestMethod.POST)
	public String quiz(UserVO2 user) {
		
		if (user.getUserId().equals("abc1234")
				&& user.getUserPw().equals("xxx4321")) {
			return "request/login-success";			
		}
		return "request/login-fail";
	}
}
