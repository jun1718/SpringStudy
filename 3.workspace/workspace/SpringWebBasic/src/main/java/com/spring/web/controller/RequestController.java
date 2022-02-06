package com.spring.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//클라이언트 측에서 요청을 보냈을때 그것을 처리하는 컨트롤러
//디스패쳐서블릿이라는 프론트 컨트롤러가 RequestController를 찾게하려면
//디스패쳐서블릿과 requestController의 의존관계를 설정을 해야한다.
//의존관계설정을 우리가 따로할 필요는 없고 해당 클래스에 대한 빈객체를 실행시 만들어지게만 하면된다.
//근데 helloController는 빈등록 코드를 자동으로 이루어지게 했으니 다른 컨트롤러들도 
//자동 빈등록 코드와 아노테이션을 이용하여 그러헥 만들면 된다.

@Controller	
public class RequestController {

	//RequestMapping은 어떤 URI로 이 메서드를 동작시킬 것인가에 대한 설정임
	//이거 value = "", GET 이런 설정안하면 post든 get이든 모두 받겠다는 의미임
	@RequestMapping("/request/test")
	public String testCall() {
		System.out.println("/request/test 요청이 들어옴!");
		return "test";
	}
	
	@RequestMapping("/request/req")
	public String req() {
		System.out.println("/request/req 요청이 들어옴~!");
		return "request/req-ex01";
	}
	
//	@RequestMapping(value = "/request/basic01", method = RequestMethod.GET)
	@GetMapping("/request/basic01")
	public String basicGet() {
		System.out.println("/request/basic01요청이 들어옴: GET");
		return "request/req-ex01";
	}
	
//	@RequestMapping(value = "/request/basic01", method = RequestMethod.POST)
	@PostMapping("/request/basic01") 
	public String basicPost() {
		System.out.println("/request/basic01요청이 들어옴: POST");
		return "request/req-ex01";
	}
	
	// 요청 파라미터 받아보기
	@GetMapping("/request/param")
	public String paramTest(HttpServletRequest request) {
		System.out.println("request/param 요청: GET");
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		
		return "";
	}
	
}
