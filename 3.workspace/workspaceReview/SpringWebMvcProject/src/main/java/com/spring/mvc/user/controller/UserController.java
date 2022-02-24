package com.spring.mvc.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String loginCheck(@RequestBody UserVO inputData) {
		String result = null;
		/*
		 # 클라이언트가 전송한 id값과 pw값을 가지고 DB에서 회원의 정보를 조회해서 불러온다음
		 	값 비교를 통해
		 	1. 아이디가 없을 경우 클라이언트측으로 문자열 "idFail" 전송 
		 	2. 비밀번호가 틀렸을 경우 문자열 "pwFail" 전송
		 	3. 로그인 성공시 문자열 "loginSuccess" 전송

			전송은 클라이언트에게 하는거고 클라이언트 콘솔에서 확인하면 되느니라
		 */
		
		UserVO dbData = service.selectOne(inputData.getAccount());
		
		if (dbData == null) {
			return "idFail";
		}
		
		
		if (!dbData.getPassword().equals(inputData.getPassword())) {
			return "pwFail";
		}
		
		result = "loginSuccess";
		
		
		return result;
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
