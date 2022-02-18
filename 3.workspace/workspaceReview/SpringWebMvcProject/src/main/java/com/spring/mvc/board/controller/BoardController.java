package com.spring.mvc.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.service.IBoardService;

@Controller
@RequestMapping("/board")

public class BoardController {
	@Inject
	private IBoardService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		System.out.println("URL : /board/list => GET");
		List<BoardVO> articles = service.getArticleList();
		model.addAttribute("articles", articles);
		articles.forEach(article -> System.out.println(article));
	}
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void write() {
		System.out.println("URL : /board/write => GET");
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(BoardVO article, RedirectAttributes ra) {
		System.out.println("URL : /board/write => POST");
		service.insert(article);
		ra.addFlashAttribute("msg", "regSuccess");
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/content/{boardNo}", method = RequestMethod.GET)
	public String content(@PathVariable Integer boardNo, Model model) {
		System.out.println("URL: /board/content => GET");
		model.addAttribute("article", service.getArticle(boardNo));
		return "board/content";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String content(Integer boardNo, RedirectAttributes ra) {
		service.delete(boardNo);
		ra.addFlashAttribute("msg", "delSuccess");
		return "redirect:/board/list";
	}
	

}
