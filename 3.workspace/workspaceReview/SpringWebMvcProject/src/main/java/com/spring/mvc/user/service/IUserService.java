package com.spring.mvc.user.service;

import java.util.Date;
import java.util.List;

import com.spring.mvc.user.model.UserVO;

public interface IUserService {
	void register(UserVO user);
	
	Integer checkId(String account);
	
	
	void delete(String account);
	
	UserVO selectOne(String account);
	
	List<UserVO> selectAll();
	
	void keepLogin(String sessionId, Date limitTime, String account);
	
	UserVO getUserWithSessionId(String sessionId);
}
