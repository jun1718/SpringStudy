package com.spring.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.web.model.UserVO;
import com.spring.web.model.UserVO2;

//클라이언트 측에서 요청을 보냈을때 그것을 처리하는 컨트롤러
//디스패쳐서블릿이라는 프론트 컨트롤러가 RequestController를 찾게하려면
//디스패쳐서블릿과 requestController의 의존관계를 설정을 해야한다.
//의존관계설정을 우리가 따로할 필요는 없고 해당 클래스에 대한 빈객체를 실행시 만들어지게만 하면된다.
//근데 helloController는 빈등록 코드를 자동으로 이루어지게 했으니 다른 컨트롤러들도 
//자동 빈등록 코드와 아노테이션을 이용하여 그러헥 만들면 된다.

@Controller	
@RequestMapping("/request") // 공통 uri 떼놓기
public class RequestController {

	//RequestMapping은 어떤 URI로 이 메서드를 동작시킬 것인가에 대한 설정임
	//이거 value = "", GET 이런 설정안하면 post든 get이든 모두 받겠다는 의미임
//	@RequestMapping("/request/test")
	@RequestMapping("/test") // 원래 위에처럼 해야하지만 클래스선언분위에 일괄매핑을 했어서 괜찮다.
	public String testCall() {
		System.out.println("/request/test 요청이 들어옴!");
		return "test";
	}
	
//	@RequestMapping("/request/req") // 이대로 두면 /request/request/req 해야지 온다.
	@RequestMapping("/req")
	public String req() {
		System.out.println("/request/req 요청이 들어옴~!");
		return "request/req-ex01";
	}
	
	@GetMapping("/req-ex02")
	public void reqEx02() {
		System.out.println("/reqeust/req-ex02 요청!");
	}
	
//	@RequestMapping(value = "/request/basic01", method = RequestMethod.GET)
//	@GetMapping("/request/basic01")
	@GetMapping("/basic01")
	
	public String basicGet() {
		System.out.println("/request/basic01요청이 들어옴: GET");
		return "request/req-ex01";
	}
	
//	@RequestMapping(value = "/request/basic01", method = RequestMethod.POST)
//	@PostMapping("/request/basic01")
	@PostMapping("/basic01")
	public String basicPost() {
		System.out.println("/request/basic01요청이 들어옴: POST");
		return "request/req-ex01";
	}
	
	// 요청 파라미터 받아보기
//	@GetMapping("/request/param")
	@GetMapping("/param")
	public String paramTest(HttpServletRequest request) {
		System.out.println("request/param 요청: GET");
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		
		return "";
	}
	
	
	/////////////////////////////////////////////////////////////////////
	@GetMapping("/join")
	public void register() {
		System.out.println("/request/join : GET");
	}
	
	/*
	 # 1.전통적인 jsp/servlet의 파라미터 읽기처리방법
	 	-HttpServletRequest객체를 사용
	 
	
	@PostMapping("/join")
	public String register(HttpServletRequest request) {
		System.out.println("/request/join : POST");
		System.out.println("ID : " + request.getParameter("userId"));
		System.out.println("PW : " + request.getParameter("userPw"));
		System.out.println("HOBBY : " 
				+ Arrays.toString(request.getParameterValues("hobby")));
		return "request/join";
	}
	*/
	
	/* 
	 # 2.@RequestParam 아노테이션을 이용한 요청 파라미터 처리
	 	-1번 안쓰는 이유 : 받아야할 파라미터가 많다면 request함수를 넘많이써야함. 나이가 들어오면
	 		변환처리를 해야한다. request의 리턴타입은 항상 String이기때문에 변환과정이 추가돼서 별로다.
	 		즉 형변환 처리의 귀찮음을 막아주는 법
	 	-2번단점 : 현재경우 hobby취미를 선택하지 않으면 에러난다. 즉 매개변수로 hobby값이 null이 오면 에러다
	 		@RequestParam(value = "hobby", required = false) List<String> hobbys) {
	 		위처럼하면 해결됨 f3보면 required가 true로 되어있어서에러인거다
	 		근데 이렇게해도 null이 들어오니까 문법에러가 나긴함 왜냐면 아래 .toString()을 했기때문
	 		그럼 @RequestParam(value = "hobby", required = false, defaultValue = "no hobby person") List<String> hobbys) {
	 		하면 됨
	 		
	 	**별개로 한글로 입력하면 깨진다. 톰캣이 한글을 못알아먹어서 그렇다.
	 		톰캣한글처리에 대해 편하게 할수있는 방법을 spring에서 제공한다.
	 		
	 
	@PostMapping("/join")
	public void register(@RequestParam("userId") String id,
							@RequestParam("userPw") String pw,
							@RequestParam(value = "hobby", required = false, defaultValue = "no hobby person") List<String> hobbys) {
			//String id = request.getParameter("userId"); 를 한번에 처리해주는 것임
			//int라면 형변환도 int로 해서 알아서 됨
			
		System.out.println("ID : " + id);
		System.out.println("PW : " + pw);
		System.out.println("HOBBY : " + hobbys.toString());
	}
	
	*/
	
	/*
	 # 3.커맨드 객체를 활용한 파라미터 처리
	 	-UserVO의 필드명과 jsp의 파라미터명이 같고 UserVo라는 객체에 setter getter가 있다면 그대로 바로 형변환
	 	될건 되면서 알아서 다 들어간다. 자동화의 신세계!
	 */
	@PostMapping("/join")
	public void register(UserVO user) {
		System.out.println("ID : " + user.getUserId());
		System.out.println("PW : " + user.getUserPw());
		System.out.println("NAME : " + user.getUserName());
		System.out.println("HOBBY : " + user.getHobby());
	}
	
	@GetMapping("/quiz")
	public String quiz() {
		System.out.println("/request/req-quiz 요청됨 : GET");
		return "/request/req-quiz"; // / 붙여도 되고 안붙여도 됨
	}
	
	@PostMapping("/quiz")
	public String quiz(UserVO2 user) {
		System.out.println("/request/req-quiz 요청됨 : POST");
		
		if (user.getUserId().equals("abc1234") 
				&& user.getUserPw().equals("xxx4321")) {
			return "request/login-success";	
		}
		
		return "request/login-fail";
	}
	
}
