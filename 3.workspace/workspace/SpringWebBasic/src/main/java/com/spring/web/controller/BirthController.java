package com.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.web.model.BirthVO;

@Controller

public class BirthController {
	//생일을 입력하는 폼을 열어주는 요청메서드
	@GetMapping("/birth")
	public String birthForm() {
		return "/birth/birth-form";
	}
	
	//생일 정보를 result페이지에 전달하는 요청메서드
	@PostMapping("/birth")
	public String birthForm(@ModelAttribute("user") BirthVO user) {
		int month = user.getMonth().charAt(0) - 48;
		String result = "";
		if (month >= 1 && month <= 9) {
			result = "0";
			result += month;
		} else {
			result = String.valueOf(month);
		}
		
		System.out.println(user.getYear() + result + user.getDay());
		return "/birth/birth-result";
	}
}
