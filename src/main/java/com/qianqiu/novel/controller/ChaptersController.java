package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Chapters;
import com.qianqiu.novel.service.ChaptersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("chapters")
public class ChaptersController {

	@Resource
	ChaptersService service;

}
