package com.spring.mvc.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	//게시글 목록 불러오기 요청
	/*
	@GetMapping("/list")
	public void list(Model model) {
		List<BoardVO> list = service.getArticleList();
		System.out.println("URL : /board/list GET -> result : " + list.size());
//		list.forEach(article -> System.out.println(article));
		
		model.addAttribute("articles", list);
//		return "board/list";
	}
	*/
	
	//페이징 처리 이후 게시글 목록 불러오기 요청
	/*
	@GetMapping("/list")
	public void list(PageVO paging, Model model) {
		List<BoardVO> list = service.getArticleListPaging(paging);
		
		System.out.println("URL : /board/list GET -> result : " + list.size());
		System.out.println("parameter(페이지번호) : " + paging.getPage() + "번");
//		list.forEach(article -> System.out.println(article));
		
		PageCreator pc = new PageCreator();
		
		pc.setPaging(paging);
		pc.setArticleTotalCount(service.countArticles());
		System.out.println("pc : " + pc);
		
		model.addAttribute("articles", list);
		model.addAttribute("pc", pc);
		
//		return "board/list";
	}
	*/
	
	//검색처리 이후 게시물 목록 불러오기 요청
	@GetMapping("/list")
	public void list(SearchVO search, Model model) {
		String condition = search.getCondition();
		
		System.out.println("URL : /board/list GET -> result : ");
		System.out.println("parameter(페이지번호) : " + search.getPage() + "번");
		System.out.println("검색 조건 : " + search.getCondition());
		System.out.println("검색어 : " + search.getKeyword());
		
		PageCreator pc = new PageCreator();		
		pc.setPaging(search);
		
		System.out.println("pc : " + pc);
		System.out.println("toUriString() : " + pc.makeURI(search.getPage()));
		
		List<BoardVO> list = service.getArticleList(search);
		pc.setArticleTotalCount(service.countArticles(search));
		
		
		/*
		List<BoardVO> list = null;
		
		if (condition.equals("title")) {
			 list = service.getArticleListByTitle(search);
			 pc.setArticleTotalCount(service.countArticleByTitle(search));
			
		} else if (condition.equals("writer")) {
			list = service.getArticleListByWriter(search);
			pc.setArticleTotalCount(service.countArticleByWriter(search));
		} else if (condition.equals("content")) {
			list = service.getArticleListByContent(search);
			pc.setArticleTotalCount(service.countArticleByContent(search));
		}else if (condition.equals("titleContent")) {
			list = service.getArticleListByTitleContent(search);
			pc.setArticleTotalCount(service.countArticleByTitleContent(search));
		} else {
			list = service.getArticleListPaging(search);
			pc.setArticleTotalCount(service.countArticles());
		}
		*/
		
		
		model.addAttribute("articles", list);
		model.addAttribute("pc", pc);

	}
	
	//게시글 작성 페이지 요청
	@GetMapping("/write")
	public void write() {
		System.out.println("URL : /board/write => GET");
	}
	
	//게시글 등록 요청
	@PostMapping("/write")
	public String write(BoardVO article, RedirectAttributes ra) {
		System.out.println("URL : /board/write => POST");
		System.out.println("Controller parameter : " + article);
		service.insert(article);
		ra.addFlashAttribute("msg", "regSuccess");
		
		return "redirect:/board/list";
	}
	
	
	//게시물 상세 조회 요청
	@GetMapping("/content/{boardNo}")
	//public String content(@PathVariable("boardNo") Integer boardNo, Model model) {
							//PathVariable로 경로에서 받은 놈 필드명을 가져오고 그 필드명이
					//Integer boardNo와 같으면 PathVariable뒤에 전달인자를 줄 필요 없다.
	public String content(@PathVariable Integer boardNo, Model model
							, @ModelAttribute("p") SearchVO search) {
		System.out.println("URL: /board/content => GET");
		BoardVO article = service.getArticle(boardNo);
		System.out.println("result data: " + article);
		model.addAttribute("article", article);
		
		PageCreator pc = new PageCreator();
		pc.setPaging(search);
		model.addAttribute("pc", pc);
		return "board/content";
	}
	
	//게시물 삭제 요청
	/*
	@GetMapping("/delete")
	public String delete(Integer boardNo, RedirectAttributes ra) {
		service.delete(boardNo);
		ra.addFlashAttribute("msg", "delSuccess");
		return "redirect:/board/list";
	}
	*/
	
	@PostMapping("/delete")
	public String delete(Integer boardNo, RedirectAttributes ra, PageVO paging) {
		System.out.println("URL: /board/delete => POST");
		System.out.println("Controller Paramerter : " + boardNo);
		service.delete(boardNo);
//		ra.addFlashAttribute("msg", "delSuccess");
//		ra.addAttribute("page", paging.getPage());
//		ra.addAttribute("countPerPage", paging.getCountPerPage());
		ra.addFlashAttribute("msg", "delSuccess")
		  .addAttribute("page", paging.getPage())
		  .addAttribute("countPerPage", paging.getCountPerPage());
		//원시적인방법
//		return "redirect:/board/list?page="+paging.getPage()+"&countPerPage="+paging.getCountPerPage();
		return "redirect:/board/list";
	}
	
	@GetMapping("/modify")
	public String modify(Integer boardNo, Model model, @ModelAttribute("p") PageVO paging) {
		System.out.println("URL: /board/modify => GET");
		System.out.println("Parameter(글 번호) : " + boardNo);
		model.addAttribute("article", service.getArticle(boardNo));
		
		return "board/modify";
	}
	
	//게시물 수정 요청
	@PostMapping("/modify")
	public String modify(BoardVO article, RedirectAttributes ra) {
		System.out.println("URL: /board/modify => POST");
		System.out.println("parameter(게시글): " + article);
		
		service.update(article);
		
		ra.addFlashAttribute("msg", "modSuccess");
		
		return "redirect:/board/content/" + article.getBoardNo();
	}
	
}
