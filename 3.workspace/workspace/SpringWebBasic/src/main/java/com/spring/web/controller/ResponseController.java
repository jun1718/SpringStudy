package com.spring.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.web.model.UserVO;
import com.spring.web.model.UserVO2;

@Controller("resCon")
@RequestMapping("/response")

public class ResponseController {
	
	public ResponseController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/res-ex01")
	public void resEx01() {
		
	}
	
	//1.Model 객체를 사용하여 화면에 데이터 전송하기
	/*
	@GetMapping("/test")
	public void test(@RequestParam("age") int age,
						Model model) {
		model.addAttribute("nick", "뽀삐");
		model.addAttribute("age", age);
	}
	*/
	
	//@ModelAttribute : @RequestParam("age") int age + model.addAttribute("age", age)
					//와 같은 것이다. 포함관계
	//2.@ModelAttribute를 사용한 화면에 데이터 전송처리
	@GetMapping("/test")
	public void test(@ModelAttribute("age") int age,
			Model model) {
		model.addAttribute("nick", "뽀삐");
//		model.addAttribute("age", age);
	}
	
	//3.ModelAndView 객체를 활용한 처리
	@GetMapping("/test2")
	public ModelAndView test2() {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("userName", "박영희"); //화면에 전달할 데이터 래핑
//		mv.setViewName("/response/test2"); //viewResolver에게 전달할 문자열
//		return mv;
		
		return new ModelAndView("/response/test2", "userName", "박영희");
	}
	
	
	//res-ex02.jsp파일을 열람하는 요청메서드 작성
	@GetMapping("/res-ex02")
	public void resEx02() {
		
	}
	
	
	/*
	@PostMapping("/join")
	public String join(UserVO user, Model model) {
		model.addAttribute("user", user);
		return "response/test3";
	}
	*/
	@PostMapping("/join")
	public String join(@ModelAttribute("user") UserVO user) {
		return "response/test3";
	}
	
	@GetMapping("/res-quiz")
	public void resQuiz() {
		
	}
	
	@PostMapping("/res-login")
	public String resLogin(@ModelAttribute("user") UserVO2 user) {
		String id = user.getUserId();
		String pw = user.getUserPw();
		
		if (id.equals("kim123") && pw.equals("kkk1234")) {
			return "response/res-quiz-success";			
		}
		
		return "response/res-quiz-fail";
	}
	
	
	///////////////////////////////////////////////////////////////////
	//Redirect처리 
	//원래는 viewResolver에게 보내는 방식이 포워드 방식이다.
	//글쓰기 요청이 끝난다음에 글목록 페이지가 떠야하는데 포워드면 요청끝나고 디스패쳐왔다가 다시 db에 가야함
	//글쓰기 요청(insert)가 되고나면 다시 돌아오지않고 재요청으로  select하여 목록을 가져오면 좋다.
	//그 재요청방법이 Redirect처리다.
	//글 삭제같은경우도 delete로 게시글 지우고 목록을 보여줄때 디스패쳐까지 돌아오지않고 재요청을해서 바로 db에서 목록을
	//가져오는 것도 포워드로는 않되고 Redirect로 가능하다.
	
	//로그인 화면 요청처리
	@GetMapping("/login")
	public String login() {
		System.out.println("/response/login 요청 발생 : GET");
		return "response/res-redirect-form";
	}
	
	//로그인 검증처리
	/*
	@PostMapping("/login")
	public String login(@ModelAttribute("userId") String id
							, @RequestParam("userPw") String pw
							, @RequestParam("userPwChk") String pwChk
							, Model model
							//, HttpServletResponse response)원래 이게 jsp방식인데 spirng에선 이렇게 안해도됨
							, HttpSession session
							){
		System.out.println("/response/login 요청 발생 : POST");
		System.out.println("id : " + id); 
		System.out.println("pw : " + pw);
		System.out.println("pwChk : " + pwChk);
		
		
		
		if (id.equals("")) { // 아이디 입력하지 않은 경우
//			response.sendRedirect(); 원래 이게 jsp방식인데 spirng에선 이렇게 안해도됨
			System.out.println("아이디가 비었습니다.");
//			model.addAttribute("msg", "아이디는 필수 값이에요!");
			session.setAttribute("msg", "아이디는 필수 값이에요!");
			return "redirect:/response/login"; // jsp를 열러가지않고 바로 다시 controller의 
												// /resposne/login uri를 받을수있는 메서드를
												// 찾는다.
												//이때 redirect로 보내면 무조건 get요청이다.
												//그래서 위에 로그인화면요청처리로 간다.
		} else if (!pw.equals(pwChk)) { // 비번과 확인비번이 다른 경우
			System.out.println("두 비밀번호가 같지 않습니다.");
//			model.addAttribute("msg", "비밀번호 확인란을 체크하세요!");
			session.setAttribute("msg", "두 비밀번호가 같지 않습니다.");
			return "redirect:/response/login";
		} else if (id.equals("abc123") && pw.equals("1234")) {
			System.out.println("로그인성공!");
			return "response/res-quiz-success";
		} else { // 안전빵
			System.out.println("그런 아이디와 비번은 등록되지 않았습니다.");
//			model.addAttribute("msg", "그런 아이디와 비번은 등록되지 않았습니다.");
			return null;
		}
	}
	*/
	@PostMapping("/login")
	public String login(@ModelAttribute("userId") String id
							, @RequestParam("userPw") String pw
							, @RequestParam("userPwChk") String pwChk
							, RedirectAttributes ra
//							, HttpSession session
							, Model model
							){
		System.out.println("/response/login 요청 발생 : POST");
		System.out.println("id : " + id); 
		System.out.println("pw : " + pw);
		System.out.println("pwChk : " + pwChk);
		
		
		
		if (id.equals("")) { // 아이디 입력하지 않은 경우
			System.out.println("아이디가 비었습니다.");
			ra.addFlashAttribute("msg2", "아이디는 필수 값이에요!");
//			session.setAttribute("msg1", "아이디는 필수 값이에요!");
//			model.
			return "redirect:/response/login"; 
		} else if (!pw.equals(pwChk)) { // 비번과 확인비번이 다른 경우
			System.out.println("두 비밀번호가 같지 않습니다.");
			ra.addFlashAttribute("msg2", "두 비밀번호가 같지 않습니다.");
//			session.setAttribute("msg1", "두 비밀번호가 같지 않습니다.");
			return "redirect:/response/login";
		} else if (id.equals("abc123") && pw.equals("1234")) {
			System.out.println("로그인성공!");
			
			return "response/res-quiz-success";
		} else { // 안전빵
			System.out.println("그런 아이디와 비번은 등록되지 않았습니다.");
			return null;
		}
	}
	
	
}