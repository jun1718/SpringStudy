package com.spring.mvc.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String register(@RequestBody UserVO user) {
		service.register(user);
		System.out.println("입력성공!");
		return "joinSuccess";
	}
	
	@RequestMapping(value = "/checkId", method = RequestMethod.POST) 
	public String checkId(@RequestBody String account) {
		String result = "";
		System.out.println("account : " + account);
		
		if (service.checkId(account) == 0) {
			System.out.println("아이디 사용가능!");
			result = "OK";
		} else {
			System.out.println("아이디 사용불가, 중복됨");
			result =  "NO";
		}
		
		System.out.println("result : " + result);
		return result;
	}
	
	// 로그인 요청 처리
	@PostMapping("/loginCheck")
	public String loginCheck(@RequestBody UserVO inputData, HttpSession session,
								HttpServletResponse response) {
		String result = null;
		/*
		 # 클라이언트가 전송한 id값과 pw값을 가지고 DB에서 회원의 정보를 조회해서 불러온다음
		 	값 비교를 통해
		 	1. 아이디가 없을 경우 클라이언트측으로 문자열 "idFail" 전송 
		 	2. 비밀번호가 틀렸을 경우 문자열 "pwFail" 전송
		 	3. 로그인 성공시 문자열 "loginSuccess" 전송

			전송은 클라이언트에게 하는거고 클라이언트 콘솔에서 확인하면 되느니라
		 */
		
		System.out.println("inputData : " + inputData);
		
		UserVO dbData = service.selectOne(inputData.getAccount());
		
		if (dbData == null) {
			return "idFail";
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if (!encoder.matches(inputData.getPassword(), dbData.getPassword())) {
			return "pwFail";
		}
		
		result = "loginSuccess";
		session.setAttribute("login", dbData);
		
		if (inputData.isAutoLogin()) {
			Cookie loginCookie = new Cookie("loginCookie", session.getId());
			long limitTime = 60 * 60 * 24 * 90;
			
			
			loginCookie.setPath("/");
			loginCookie.setMaxAge((int) limitTime);
			
			
			Date limitDate = new Date(System.currentTimeMillis() + (limitTime * 1000));

			
			response.addCookie(loginCookie);
			service.keepLogin(session.getId(), limitDate, dbData.getAccount());
			
		}
		
		return result;
	}
	
	@GetMapping("/logout") 
	public ModelAndView logout(HttpSession session, HttpServletRequest request,
								HttpServletResponse response) {
		UserVO user = (UserVO) session.getAttribute("login");
		
		if (user != null) {
			session.removeAttribute("login");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if (loginCookie != null) {
				loginCookie.setMaxAge(0);		
				response.addCookie(loginCookie);
				
				service.keepLogin("none", new Date(), user.getAccount());
			}
			
		}
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/{account}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String account) {
		service.delete(account);
		System.out.println("삭제 완료!");
		return "delSuccess";
	}
	
	@RequestMapping(value = "/{account}", method = RequestMethod.GET)
	public UserVO selectOne(@PathVariable String account) {
		System.out.println("한개 조회 성공!");
		return service.selectOne(account);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<UserVO> selectAll() {
		System.out.println("전체 조회 성공!");
		return service.selectAll();
	}
}
