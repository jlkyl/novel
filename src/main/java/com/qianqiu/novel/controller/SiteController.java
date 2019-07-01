package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Site;
import com.qianqiu.novel.service.SiteService;
import com.qianqiu.novel.utils.MyUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("site")
public class SiteController {

	@Resource
	SiteService service;

	@RequestMapping("list")
	public List<Site> list(){
		return service.findAll();
	}
	@RequestMapping("add")
	public Integer add(Site site, HttpSession session){
		site.setOperateeid(MyUtil.getempid(session));
		return service.add(site);
	}
	@RequestMapping("update")
	public Integer update(Site site, HttpSession session){
		site.setOperateeid(MyUtil.getempid(session));
		return service.update(site);
	}
}
