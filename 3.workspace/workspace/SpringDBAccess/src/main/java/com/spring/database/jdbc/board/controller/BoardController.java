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
	
	//등록
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void write() {
		System.out.println("/board/write 요청됨 : GET");
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(BoardVO article) {
		System.out.println("/board/write 요청됨 : POST");
		service.insertArticle(article);
		return "redirect:/board/list";
	}
	
	//목록보기
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		List<BoardVO> articles = service.getArticles();
		model.addAttribute("articles", articles);
	}
	
	//삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(int boardNo) {
		System.out.println("/board/delete?boardNo=" + boardNo + " 요청됨 : GET");
		service.deleteArticle(boardNo);
		return "redirect:/board/list";
	}
	
	//게시글확인(상세보기)
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public void content(int boardNo, Model model) {
		System.out.println("/board/content 요청됨 : GET");
		BoardVO article = service.getContent(boardNo);
		model.addAttribute("article", article);
	}
	
	//게시글 수정
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modify(int boardNo, Model model) {
		System.out.println("/board/modify 요청됨 : GET");
		BoardVO article = service.getContent(boardNo);
		model.addAttribute("article", article);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO article) {
		System.out.println("/board/modify 요청됨 : POST");
		service.modifyArticle(article);
		return "redirect:/board/content?boardNo=" + article.getBoardNo();
	}
	
	
	@RequestMapping(value = "/searchList", method = RequestMethod.GET)
	public String searchList(String keyword, Model model) {
		List<BoardVO> list = service.getSearchList(keyword);
		model.addAttribute("articles", list);
		
		return "board/list";
	}
	
}
