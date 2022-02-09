package com.spring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.web.model.ScoreVO;
import com.spring.web.service.IScoreService;

@Controller
@RequestMapping("/score")
public class ScoreController {
	@Autowired
	private IScoreService service;
	
	//점수 등록 화면을 열어주는 처리를 하는 요청메서드
	@GetMapping("/register")
	public String register() {
		System.out.println("/score/register: GET");
		return "score/write-form";
	}
	
	//점수 등록을 처리하는 요청 메서드
	@PostMapping("/register")
	public String register(ScoreVO scores) {
		System.out.println("/score/register: POST");
		System.out.println("Controller param: " + scores);
		
		service.insertScore(scores);
		return "score/write-result";
	}
	
	//점수 전체 조회를 처리하는 요청 메서드
	@GetMapping("/list")
	public void list(Model model) {
		System.out.println("/score/list: GET");
		List<ScoreVO> list = service.selectAllScores();
		model.addAttribute("sList", list);
		
	}
	
	//점수 삭제 요청 처리 메서드
	@GetMapping("/delete")
	public String delete(@RequestParam("stuNum") int stuNum, RedirectAttributes ra) {
		List<ScoreVO> list = service.selectAllScores();
		service.deleteScore(stuNum);
		ra.addFlashAttribute("message", "delSuccess");
		return "redirect:/score/list";
	}
	
	//점수 개별 조회 화면 열람요청 메서드
	@GetMapping("/search")
	public void search() {
		System.out.println("/score/search: GET");
		
	}
	
	@PostMapping("/selectOne")
	public String selectOne(String stuNum, Model model, RedirectAttributes ra) {
		
		/*
		try {
			ScoreVO stu = service.selectOneScore(Integer.parseInt(stuNum));			
			model.addAttribute("stu", stu);
			return "/score/select-result";
		} catch(NumberFormatException e) {
			ra.addFlashAttribute("message", "숫자로만 입력하세요!");
			return "redirect:/score/search"; 
		} catch(IndexOutOfBoundsException e) {
			ra.addFlashAttribute("message", "학번정보가 없습니다.");
			return "redirect:/score/search";
		} 
		*/
		List<ScoreVO> list = service.selectAllScores();
		
		try {
			int n = Integer.parseInt(stuNum);
			if (n > list.size()) {
				ra.addFlashAttribute("message", "학번정보가 없습니다.");
				return "redirect:/score/search";
			}
			
			ScoreVO stu = service.selectOneScore(n);			
			model.addAttribute("stu", stu);
			return "score/select-result";
		} catch(NumberFormatException e) {
			ra.addFlashAttribute("message", "숫자로만 입력하세요!");
			return "redirect:/score/search"; 
		} 
	}
}
