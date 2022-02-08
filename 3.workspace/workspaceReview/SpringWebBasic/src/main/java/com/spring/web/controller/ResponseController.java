package com.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.web.model.UserVO;

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
	////////////////////////////////
	///////////////////////////////////////
	
	
	@RequestMapping(value = "/res-ex02", method = RequestMethod.GET)
	public void resEx02() {
		
	}
	
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute("user") UserVO user) {
		return "response/test3";
	}

	@RequestMapping(value = "/res-quiz", method = RequestMethod.GET) 
	public void resQuiz() {
		
	}
	
	@RequestMapping(value = "/res-login", method = RequestMethod.POST)
	public String resLogin(@ModelAttribute("userId") String id,
							@RequestParam("userPw") String pw) {
		if (id.equals("kim123") && pw.equals("kkk1234")) {
			return "response/res-quiz-success";			
		}
		return "response/res-quiz-fail";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		System.out.println("/login 요청됨 : GET");
		return "response/res-redirect-form";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("userId") String id,
							@RequestParam("userPw") String pw,
							@RequestParam("userPwChk") String pwChk,
							RedirectAttributes ra) {
		System.out.println("/login 요청됨 : POST");
		
		System.out.println("id : " + id);
		System.out.println("pw : " + pw);
		System.out.println("pwChk : " + pwChk);
		
		if (id.equals("")) {
			System.out.println("아이디는 필수입력임 ㅋ");
			ra.addFlashAttribute("msg", "아이디는 필수 값이에요!");
			return "redirect:/response/login";
		} else if (!pw.equals(pwChk)) {
			System.out.println("비번 틀렸자냐아아앙~! 확인좀 잘좀해라!");
			ra.addFlashAttribute("msg", "비밀번호 확인란을 체크하세요!");
			return "redirect:/response/login";
		} else if (id.equals("abc123") && pw.equals("1234")) {
			return "response/res-quiz-success";
		} else {
			return "null";			
		}
	}
}
