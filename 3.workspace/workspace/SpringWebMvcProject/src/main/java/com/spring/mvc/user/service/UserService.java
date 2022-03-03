package com.spring.mvc.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.repository.IUserMapper;


@Service
public class UserService implements IUserService {

	@Autowired
	private IUserMapper mapper;
	
	@Override
	public void register(UserVO user) {
		// TODO Auto-generated method stub
		System.out.println("암호화 전 : " + user.getPassword());
		
		// 회원 비밀번호를 암호화 인코딩
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		// 비밀번호를 암호화하여 다시 user 객체에 저장
		String securePw = encoder.encode(user.getPassword());
		user.setPassword(securePw);

		System.out.println("암호화 후 : " + securePw);
		mapper.register(user);
	}
	
	@Override
	public Integer checkId(String account) {
		// TODO Auto-generated method stub
		return mapper.checkId(account);
	}
	
	@Override
	public void delete(String account) {
		// TODO Auto-generated method stub
		mapper.delete(account);
	}
	
	@Override
	public UserVO selectOne(String account) {
		// TODO Auto-generated method stub
		return mapper.selectOne(account);
	}
	
	@Override
	public List<UserVO> selectAll() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}
	
	
	@Override
	public void keepLogin(String sessionId, Date limitDate, String account) {
		// TODO Auto-generated method stub
		Map<String, Object> datas = new HashMap<>();
		datas.put("sessionId", sessionId);
		datas.put("limitDate", limitDate);
		datas.put("account", account);
				
		mapper.keepLogin(datas);
	}
	
	@Override
	public UserVO getUserWithSessionId(String sessionId) {
		// TODO Auto-generated method stub
		return mapper.getUserWithSessionId(sessionId);
	}
}
