package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Emps;
import com.qianqiu.novel.entity.Menus;
import com.qianqiu.novel.entity.Pages;
import com.qianqiu.novel.service.MenusService;
import com.qianqiu.novel.utils.MyUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("menu")
public class MenusController {

	@Resource
	MenusService service;

	@RequestMapping("list")
	public List<Menus> list(){
		return service.findAll();
	}

	@RequestMapping("pageList")
	public Pages pageList(Integer page,Integer rows){
		return service.querypage(page,rows);
	}

	@RequestMapping("add")
	public Integer add(Menus menus,HttpSession session){
		menus.setOperateeid(MyUtil.getempid(session));
		return service.add(menus);
	}

	@RequestMapping("upd")

	public Integer upd(Menus menus,HttpSession session){
		menus.setOperateeid(MyUtil.getempid(session));
		return service.update(menus);
	}

	@RequestMapping("del")
	public Integer del(Integer id){
		return service.del(id);
	}

	@RequestMapping("getMenus")
	public List<Menus> getMenus(Integer roleid, HttpSession session){
		return service.findByRid(((Emps)session.getAttribute("emps")).getRoleid());
	}
}
