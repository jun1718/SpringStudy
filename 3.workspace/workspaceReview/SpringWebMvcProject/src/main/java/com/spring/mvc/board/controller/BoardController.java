package com.spring.mvc.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.service.IBoardService;
import com.spring.mvc.commons.PageCreator;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.commons.SearchVO;

@Controller
@RequestMapping("/board")

public class BoardController {
	@Inject
	private IBoardService service;
	
	/*
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		System.out.println("URL : /board/list => GET");
		List<BoardVO> articles = service.getArticleList();
		model.addAttribute("articles", articles);
		articles.forEach(article -> System.out.println(article));
	}
	*/
	
	/*
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(PageVO paging, Model model) {
		System.out.println("URL : /board/list => GET");
		System.out.println("parameter paging : " + paging);
		List<BoardVO> articles = service.getArticleListPaging(paging);
		Integer countArticles = service.getCountArticles();
		
		PageCreator pc = new PageCreator(countArticles, paging);
		System.out.println("pc : " + pc);
		
		model.addAttribute("articles", articles);
		model.addAttribute("pc", pc);
//		articles.forEach(article -> System.out.println(article));
	}
	*/
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(SearchVO search, Model model) {
		System.out.println("URL : /board/list => GET");
		System.out.println("parameter paging : " + search);
		
		String condition = search.getCondition();
		List<BoardVO> articles = service.getArticleList(search);
		PageCreator pc = new PageCreator();
		pc.setPaging(search);
		pc.setCountArticles(service.countArticles(search));
		
		
		/*
		if (condition.equals("title")) {
			articles = service.getArticleListByTitle(search);
			pc.setCountArticles(service.countArticleByTitle(search));
		} else if (condition.equals("writer")) {
			articles = service.getArticleListByWriter(search);
			pc.setCountArticles(service.countArticleByWriter(search));
		
		} else if (condition.equals("content")) {
			articles = service.getArticleListByContent(search);
			pc.setCountArticles(service.countArticleByContent(search));
			
		} else if (condition.equals("titleContent")) {
			articles = service.getArticleListByTitleContent(search);
			pc.setCountArticles(service.countArticleByTitleContent(search));
			
		} else {
			articles = service.getArticleListPaging(search);
			pc.setCountArticles(service.getCountArticles());
		}
		*/
		
//		Integer countArticles = service.getCountArticles();
		
		System.out.println("pc : " + pc);
		
		model.addAttribute("articles", articles);
		model.addAttribute("pc", pc);
//		articles.forEach(article -> System.out.println(article));
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpSession session) {
		System.out.println("URL : /board/write => GET");
			
		if (session.getAttribute("login") == null) {
			return "redirect:/"; 
		}
		
		return "board/write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(BoardVO article, RedirectAttributes ra) {
		System.out.println("URL : /board/write => POST");
		service.insert(article);
		ra.addFlashAttribute("msg", "regSuccess");
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/content/{boardNo}", method = RequestMethod.GET)
	public String content(@PathVariable Integer boardNo, Model model, @ModelAttribute("p") SearchVO search) {
		System.out.println("URL: /board/content => GET");
		model.addAttribute("article", service.getArticle(boardNo));
		return "board/content";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String content(Integer boardNo, RedirectAttributes ra, PageVO paging) {
		service.delete(boardNo);
		ra.addFlashAttribute("msg", "delSuccess")
		  .addAttribute("page", paging.getPage())
		  .addAttribute("countPerPage", paging.getCountPerPage());
	
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(Integer boardNo, Model model, @ModelAttribute("p") PageVO paging) {
		System.out.println("URL : /board/modify => GET");
		System.out.println("parameter boardNo : " + boardNo);
		
		model.addAttribute("article", service.getArticle(boardNo));
		return "board/modify";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO article, RedirectAttributes ra) {
		System.out.println("URL : /board/modify => POST");
		System.out.println("parameter article : " + article);

		service.update(article);
		ra.addFlashAttribute("msg", "modSuccess");

		return "redirect:/board/content/" + article.getBoardNo();
	}
}
