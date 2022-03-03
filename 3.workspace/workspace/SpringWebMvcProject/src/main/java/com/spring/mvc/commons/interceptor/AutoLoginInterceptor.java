package com.spring.mvc.commons.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.service.IUserService;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter{
	@Autowired 
	private IUserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		// 1. 자동 로그인 쿠키가 있는지 여부 확인
		//	-쿠키(loginCokkie)의 존재 여부 확인
		/*
		Cookie[] cookies = request.getCookies(); // 클라이언트의 모든 쿠키를 뽑아옴 그로인해 아래문제발생
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("loginCookie")) {
				//// 이런식으로 jsp에선 했는데 spring에선 원하는 쿠키만 바로 빼올수있다.
				break;
			}
		}
		*/
		HttpSession session = request.getSession();
		
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		
		if (loginCookie != null) { // 자동로그인을 했다면
			
			//2. db에서 쿠키값과 일치하는 세션 아이디를 가진 회원의 정보를 조회
			UserVO user = service.getUserWithSessionId(loginCookie.getValue());
			
			if (user != null) {
				session.setAttribute("login", user);
				
			}
		}
		
		return true; // 자동로그인이 되야하는 클라이언트든 아니든 페이지로 안내는 해야하기때문에 true를 해야함
	}
}
