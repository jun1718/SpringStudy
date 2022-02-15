package com.spring.database.jdbc.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.database.jdbc.board.model.BoardVO;
import com.spring.database.jdbc.board.service.IBoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private IBoardService service;
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void write() {
		System.out.println("/board/write 호출 : GET");
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(BoardVO article) {
		service.insertArticle(article);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		List<BoardVO> articles = service.getArticles();
		model.addAttribute("articles", articles);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(int boardNo) {
		service.deleteArticle(boardNo);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public void content(int boardNo, Model model) {
		model.addAttribute("article", service.getContent(boardNo));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modify(int boardNo, Model model) {
		model.addAttribute("article", service.getContent(boardNo));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO article) {
		service.modifyArticle(article);
		return "redirect:/board/content?boardNo=" + article.getBoardNo();
	}
	
	@RequestMapping(value = "/searchList", method = RequestMethod.GET)
	public String searchList(String keyword, Model model) {
		model.addAttribute("articles", service.getSearchList(keyword));
		return "board/list";
	}
	
}
