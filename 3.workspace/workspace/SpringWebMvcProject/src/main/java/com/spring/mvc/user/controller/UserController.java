package com.spring.mvc.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
