package com.spring.mvc.user.repository;

import java.util.List;

import com.spring.mvc.user.model.UserVO;

public interface IUserMapper {
	void register(UserVO user);
	
	void delete(String account);
	
	UserVO selectOne(String account);
	
	List<UserVO> selectAll();
	
}
