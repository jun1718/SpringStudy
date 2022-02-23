package com.spring.mvc.board.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.repository.IUserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/mvc-config.xml"})
public class UserMapperTest {
	@Autowired
	private IUserMapper mapper;
	
	@Test
	public void registerTest() {
		UserVO user = new UserVO();
		user.setAccount("abc1234");
		user.setPassword("aaa1234");
		user.setName("박용국");

		mapper.register(user);
		System.out.println("입력완료!");
	}
	
	@Test
	public void deleteTest() {
		mapper.delete("zzz4321");
		System.out.println("삭제 완료!");
	}
	@Test
	public void selectOneTest() {
		System.out.println(mapper.selectOne("abc1234"));
		System.out.println("1명 조회 완료!");
	}
	@Test
	public void selectAllTest() {
		mapper.selectAll().forEach(vo -> System.out.println(vo));
		System.out.println("전체 조회 완료!");
	}
}
