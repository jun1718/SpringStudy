package com.spring.mvc.user.service;

import java.util.List;

import com.spring.mvc.user.model.UserVO;

public interface IUserService {
	void register(UserVO user);
	
	Integer checkId(String account);
	
	
	void delete(String account);
	
	UserVO selectOne(String account);
	
	List<UserVO> selectAll();
}
