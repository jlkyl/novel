package com.qianqiu.novel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qianqiu.novel.dao.IRolesDAO;
import com.qianqiu.novel.entity.Roles;

import javax.annotation.Resource;

@Service
public class RolesService {

	@Resource
	IRolesDAO dao;

	public List<Roles> findAll(){
		return dao.findAll();
	}
	public Integer add(Roles roles){
		dao.add(roles);
		return dao.addMenus(roles);
	}
	public Integer del(Integer roleid){
		return dao.del(roleid);
	}
	public Integer upd(Roles roles){
		return dao.upd(roles);
	}
}
