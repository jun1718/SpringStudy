package com.spring.mvc.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.util.WebUtils;

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
								HttpSession session, HttpServletResponse response) {
		// 서버에서 세션 객체를 얻는 방법.
		// 1.HttpServletRequest 객체 사용
//		HttpSession session = request.getSession();
		// 2.HttpSession 객체 사용
		System.out.println("parameter: " + inputData);
		
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
			
			
			long limitTime = 60 * 60 * 24 * 90;
			// 자동 로그인 체크시 처리
			if (inputData.isAutoLogin()) {
				
				
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge((int) limitTime);
				
				response.addCookie(loginCookie);
				
				long expireDate = System.currentTimeMillis() + (limitTime * 1000);
				//현재 시간에서 3개월이 지난 시점의 밀리초를 아래 Date에 생성자로 넣어서 날짜로 변경해줌
				Date limitDate = new Date(expireDate);
						
				service.keepLogin(session.getId(), limitDate, inputData.getAccount());
			}
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
	public ModelAndView logout(HttpSession session, HttpServletRequest request, 
								HttpServletResponse response) {
		UserVO user = (UserVO) session.getAttribute("login");
		
		if (user != null) {
			session.removeAttribute("login"); //이거까지하면 더 깔끔 아래 무효화만해도됨
			session.invalidate(); // 세션무효화
			
			
			// 로그아웃 시 자동 로그인 쿠키 삭제 및 해당 회원 정보에서 session_id 제거
			/*
			 * 1.loginCookie를 읽어온뒤 해당 쿠키가 존재하는지 여부 확인
			 * 2.쿠키가 존재한다면 쿠키의 수명을 초로 다시 설정한 후 (setMaxAge사용) 
			 * 3.응답객체를 통해 로컬에 0초짜리 쿠키 재전송 -> 쿠키삭제의 의미란 이런것이다!
			 * 4.service를 통해 keepLogin을 호출하여 DB컬럼 레코드 재설정(session_id -> "none", 
			 * limit_time -> 현재시간으로)
			 */
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if (loginCookie != null) {
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				
				Date limitDate = new Date(System.currentTimeMillis());
//				service.keepLogin("none", limitDate, user.getAccount()); 이렇게해도된다 근데 그냥 newDate()만 해도 된다.
				service.keepLogin("none", new Date(), user.getAccount());
			}
			
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
