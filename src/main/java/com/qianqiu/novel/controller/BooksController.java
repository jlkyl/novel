package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Books;
import com.qianqiu.novel.service.BooksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("books")
public class BooksController {

	@Resource
	BooksService service;

	@RequestMapping("bookqueryAll")
	@ResponseBody
	public List<Map<String,Object>> queryBook(){
		return service.queryAll();
	}

	@RequestMapping("likeBooks")
	public ModelAndView likebook(String kw){
		ModelAndView mv = new ModelAndView();
		mv.addObject("llist",service.likeBook(kw));
		mv.setViewName("showAllBook");
		return mv;
	}
	/*@RequestMapping("likeBooks1")
	@ResponseBody
	public List<Map<String, Object>> likebook(String kw, HttpSession session){
		session.setAttribute("likebookKW",kw);
		return  service.likeBook(kw);
	}*/

}
