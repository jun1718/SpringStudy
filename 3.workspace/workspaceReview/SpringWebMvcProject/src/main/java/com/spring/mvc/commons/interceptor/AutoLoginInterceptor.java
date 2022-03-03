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
		
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		
		if (loginCookie != null) {
			UserVO user = service.getUserWithSessionId(loginCookie.getValue());
			
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("login", user);
			}			
		}
		
		return true;
	}
}
