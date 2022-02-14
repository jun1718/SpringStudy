package com.spring.database.jdbc.score.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.database.jdbc.score.model.ScoreVO;
import com.spring.database.jdbc.score.service.IScoreService;

@Controller
@RequestMapping("/score")
public class ScoreController {
	@Autowired
	private IScoreService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String score() {
		return "score/write-form";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String score(ScoreVO score) {
		score.setData();
		service.insertScore(score);
		return "score/write-result";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		List<ScoreVO> list = service.selectAllScores();
		model.addAttribute("list", list);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(int stuNum) {
		service.deleteScore(stuNum);
		return "redirect:/score/list";
	}
	
	@RequestMapping(value = "/selectOne", method = RequestMethod.GET)
	public String selectOneScore() {
		System.out.println("/selectOne 요청됨 : GET");
		return "score/search";
	}
	
	@RequestMapping(value = "/selectOne", method = RequestMethod.POST)
	public String selectOneScore(String stuNum, Model model, 
									RedirectAttributes ra) {
		List<ScoreVO> list = service.selectAllScores();
		System.out.println("/selectOne 요청됨 : POST");
		
		try {
			int num = Integer.parseInt(stuNum);
			
//			if (num > list.size()) {
//				ra.addFlashAttribute("message", "학번정보가 없습니다.");
//				return "redirect:/score/selectOne";
//			}
			ScoreVO stu = service.selectOneScore(num);
			
			System.out.println("stuId : " + stu.getStuId());
			model.addAttribute("stu", stu);
			return "score/select-result";
		} catch(NumberFormatException e) {
			ra.addFlashAttribute("message", "숫자로만 입력하세요!");
			return "redirect:/score/selectOne";
		} catch(IndexOutOfBoundsException e2) {
			ra.addFlashAttribute("message", "학번정보가 없습니다.");
			return "redirect:/score/selectOne";
		}
	}
}
