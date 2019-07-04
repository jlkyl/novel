package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Weekpush;
import com.qianqiu.novel.service.WeekpushService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("weekpush")
public class WeekpushController {

	@Resource
	WeekpushService service;

}
