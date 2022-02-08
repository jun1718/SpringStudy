package com.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.web.model.BirthVO;

@Controller("birC")
public class BirthController {
	@RequestMapping(value = "/birth", method = RequestMethod.GET)
	public String birth() {
		return "birth/birth-form";
	}
	
	@RequestMapping(value = "/birth", method = RequestMethod.POST)
	public String birth(@ModelAttribute("birth") BirthVO birth) {
		System.out.println(birth.getYear()+birth.getMonth()+birth.getDay());
		return "birth/birth-result";
	}
}
