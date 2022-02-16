package com.spring.database.mybatis.score.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.database.mybatis.score.model.ScoreVO;
import com.spring.database.mybatis.score.service.IScoreService;

@Controller("scoreController2")
@RequestMapping("/mybatis/score2")
public class ScoreController {
	@Autowired
	@Qualifier("scoreService2")
	private IScoreService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String score() {
		return "score2/write-form";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String score(ScoreVO score) {
		score.setData();
		service.insertScore(score);
		return "score2/write-result";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<ScoreVO> list = service.selectAllScores();
		model.addAttribute("list", list);
		return "score2/list";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(int stuNum) {
		service.deleteScore(stuNum);
		return "redirect:/mybatis/score2/list";
	}
	
	@RequestMapping(value = "/selectOne", method = RequestMethod.GET)
	public String selectOneScore() {
		System.out.println("/selectOne 요청됨 : GET");
		return "score2/search";
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
			return "score2/select-result";
		} catch(NumberFormatException e) {
			ra.addFlashAttribute("message", "숫자로만 입력하세요!");
			return "redirect:/mybatis/score2/selectOne";
		} catch(IndexOutOfBoundsException e2) {
			ra.addFlashAttribute("message", "학번정보가 없습니다.");
			return "redirect:/mybatis/score2/selectOne";
		}
	}
}
