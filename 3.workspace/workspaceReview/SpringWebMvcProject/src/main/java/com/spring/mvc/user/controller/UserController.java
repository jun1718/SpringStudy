package com.spring.mvc.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
