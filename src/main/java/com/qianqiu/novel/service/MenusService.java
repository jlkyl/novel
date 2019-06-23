package com.qianqiu.novel.service;

import java.util.List;

import com.qianqiu.novel.entity.Pages;
import org.springframework.stereotype.Service;

import com.qianqiu.novel.dao.IMenusDAO;
import com.qianqiu.novel.entity.Menus;

import javax.annotation.Resource;

@Service
public class MenusService {

	@Resource
	IMenusDAO dao;

	public List<Menus> findAll(){
		return dao.findAll(null,null);
	}
	public Pages querypage(Integer pageNum, Integer pageSize){
		Pages pages = new Pages();
		pages.setRows(dao.findAll((pageNum-1)*pageSize, pageSize));
		pages.setTotal(dao.getCount());
		return pages;
	}
	public Integer add(Menus menu){
		return dao.add(menu);
	}
	public Integer update(Menus menu){
		return dao.update(menu);
	}
	public Integer del(Integer id){
		return dao.del(id);
	}
}
