package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Chapters;
import com.qianqiu.novel.service.BooksService;
import com.qianqiu.novel.service.ChaptersService;
import com.qianqiu.novel.utils.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("chapters")
public class ChaptersController {

	@Resource
	ChaptersService service;

	@Resource
	BooksService book;

	@RequestMapping("queryAll")
	public String queryAll(Model m,Integer bookid) {
		m.addAttribute("list", service.queryAll(bookid));

		m.addAttribute("query", service.queryChapter(bookid));
		return "lookBook";
	}

	@RequestMapping("queryId")
	public String queryId(Model m,Integer chapterId,Integer rollid){

		m.addAttribute("queryId",service.queryId(chapterId,rollid));

		String read = 	FileUtil.read(service.queryId(chapterId,rollid).getUrl());

		read = read.replaceAll("(\r\n|\n)","</p><p>");

		m.addAttribute("str",	read);

		System.out.println(read);

		return "lookBook";
	}




}
