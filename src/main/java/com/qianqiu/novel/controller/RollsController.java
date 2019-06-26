package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Rolls;
import com.qianqiu.novel.service.RollsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("rolls")
public class RollsController {

	@Resource
	RollsService service;

}
