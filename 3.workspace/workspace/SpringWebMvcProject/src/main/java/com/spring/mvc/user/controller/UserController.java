package com.spring.mvc.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService service;
	
	//회원가입 요청 처리
	//Rest-api에서 Insert -> POST
	
	@PostMapping("/")
	public String register(@RequestBody UserVO user) {
		System.out.println("/user : POST");
		System.out.println("param : " + user);
		
		service.register(user);
		return "joinSuccess";
	}
	
	// 아이디 중복확인 요청처리
	@PostMapping("/checkId")
	public String checkId(@RequestBody String account) {
		System.out.println("/user/checkId : GET 요청 발생!");
		System.out.println("parameter : " + account);
		String result = null;
		
		Integer checkNum = service.checkId(account);
		if (checkNum == 1) {
			System.out.println("아이디가 중복됨!");
			result = "NO";
		} else {
			System.out.println("아이디 사용 가능!");
			result = "OK";
		}
		
		return result;
	}
	
	// 로그인 요청 처리
	@PostMapping("/loginCheck")
//	public String loginCheck(@RequestBody UserVO inputData) {
//	public String loginCheck(@RequestBody UserVO inputData, 
//								HttpServletRequest request) {
	public String loginCheck(@RequestBody UserVO inputData,
								HttpSession session) {
		// 서버에서 세션 객체를 얻는 방법.
		// 1.HttpServletRequest 객체 사용
//		HttpSession session = request.getSession();
		// 2.HttpSession 객체 사용
		
		
		String result = null;
		/*
		 # 클라이언트가 전송한 id값과 pw값을 가지고 DB에서 회원의 정보를 조회해서 불러온다음
		 	값 비교를 통해
		 	1. 아이디가 없을 경우 클라이언트측으로 문자열 "idFail" 전송
		 	2. 비밀번호가 틀렸을 경우 문자열 "pwFail" 전송
		 	3. 로그인 성공시 문자열 "loginSuccess" 전송
		 */
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		UserVO dbData = service.selectOne(inputData.getAccount());
		
		if (dbData == null) {
			result = "idFail";
			return result;
		}
		
		{ // id가 있는 경우
			if (!encoder.matches(inputData.getPassword(), dbData.getPassword())) {
				result = "pwFail";
				return result;
			}
			
			session.setAttribute("login", dbData);
			result = "loginSuccess";
		}
		
		return result;
	}
	
	//로그아웃 요청처리
	
	/* 기초적인방법
	 @GetMapping("/logout")
	public void logout(HttpSession session, HttpServletResponse response) {
		UserVO user = (UserVO) session.getAttribute("login");
		
		if (user != null) {
			session.removeAttribute("login"); //이거까지하면 더 깔끔 아래 무효화만해도됨
			session.invalidate(); // 세션무효화
			response.sendRedirect("/");
		}
	}
	 * 
	 */
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("login");
		
		if (user != null) {
			session.removeAttribute("login"); //이거까지하면 더 깔끔 아래 무효화만해도됨
			session.invalidate(); // 세션무효화
		}
		
		return new ModelAndView("redirect:/");
//		return new ModelAndView("user/login"); // 뷰리절버에 보내서 login.jsp로 보내기
	}
	
	// 회원 탈퇴 요청 처리
//	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	@DeleteMapping("/{account}")
	public String delete(@PathVariable String account) {
		
		service.delete(account);
		
		return "delSuccess";
	}

	@GetMapping("/{account}")
	public UserVO selectOne(@PathVariable String account) {
		return service.selectOne(account);
	}
	
	@GetMapping("/")
	public List<UserVO> selectAll() {
		return service.selectAll();
	}
}
