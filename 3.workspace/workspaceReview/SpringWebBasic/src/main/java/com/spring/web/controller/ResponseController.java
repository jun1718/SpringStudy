package com.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("resCon")
@RequestMapping("/response")
public class ResponseController {
	@RequestMapping(value = "/res-ex01", method = RequestMethod.GET)
	public void res() {
		System.out.println("/response/res-ex01 요청됨 : GET");
	}
	
	/*
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test(@RequestParam("age") int age, Model model) {
		System.out.println("/response/test 요청됨 : GET");
		model.addAttribute("age", age);
		model.addAttribute("nick", "뽀삐");
	}
	*/
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test(@ModelAttribute("age") int age,
						Model model) {
		System.out.println("/response/test 요청됨 : GET");
		model.addAttribute("nick", "뽀삐");
	}
	
	
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public ModelAndView test2() {
		System.out.println("/response/test2 요청됨 : GET");
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("userName", "박영희");
//		mv.setViewName("/response/test2");
//		
//		return mv;
		
		return new ModelAndView("response/test2", "userName", "박영하");
	}
	
	
}
