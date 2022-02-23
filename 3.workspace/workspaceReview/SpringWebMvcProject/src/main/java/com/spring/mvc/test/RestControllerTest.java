package com.spring.mvc.test;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RequestMapping("/test")
@RestController
public class RestControllerTest {
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
//	@ResponseBody
	public Person register() {
		Person p = new Person();
		
		p.setAge(13);
		p.setHobbys(Arrays.asList("하이", "축구", "키킥"));
		p.setName("김은주");
		
		return p;
	}
}
