package com.spring.mvc.commons.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.mvc.user.model.UserVO;

public class BoardInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("login");
		
		if (user == null) {
//			response.sendRedirect("/");
			response.setContentType("text/html; charset = utf-8");
			PrintWriter out = response.getWriter();
			
			String element = "<script>"
					+ "alert('로그인을 해야해요!');"
					+ "location.href = '/';"
					+ "</script>";
			
			
			out.print(element);
			out.flush();
			out.close();
			
			return false;
		}
		
		return true;
	}
}
