package com.spring.mvc.user.service;

import java.util.List;

import com.spring.mvc.user.model.UserVO;

public interface IUserService {
	//회원가입 기능
	void register(UserVO user);
	
	// id 중복체크 기능
	Integer checkId(String account);
		
		
	// 회원 탈퇴 기능
	void delete(String account);
	
	// 회원정보 조회기능
	UserVO selectOne(String account);
	
	// 회원 정보 전체조회 기능
	List<UserVO> selectAll();
}