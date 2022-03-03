package com.spring.mvc.user.repository;

import java.util.List;
import java.util.Map;

import com.spring.mvc.user.model.UserVO;

public interface IUserMapper {
	void register(UserVO user);
	
	Integer checkId(String account);
	
	void delete(String account);
	
	UserVO selectOne(String account);
	
	List<UserVO> selectAll();
	
	void keepLogin(Map<String, Object> map);
	
	UserVO getUserWithSessionId(String sessionId);
}