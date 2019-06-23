package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Books;
import com.qianqiu.novel.service.BooksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("books")
public class BooksController {

	@Resource
	BooksService service;

}
