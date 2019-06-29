package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Menus;
import com.qianqiu.novel.entity.Roles;
import com.qianqiu.novel.service.RolesService;
import com.qianqiu.novel.utils.MyUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("role")
public class RolesController {

	@Resource
	RolesService service;

	@RequestMapping("list")
	public List<Roles> list(){
		return service.findAll();
	}
	@RequestMapping("add")
	public Integer add(Roles roles, String mid, HttpSession session){
		addMenus(roles,mid);
		roles.setOperateeid(MyUtil.getempid(session));
		return service.add(roles);
	}
	@RequestMapping("del")
	public Integer del(Integer roleid){
		return service.del(roleid);
	}
	@RequestMapping("upd")
	public Integer upd(Roles roles,String mid,HttpSession session){
		addMenus(roles,mid);
		roles.setOperateeid(MyUtil.getempid(session));
		return service.upd(roles);
	}

	private void addMenus(Roles roles,String mid){
		if(mid!="") {
			String[] id = mid.split(",");
			for (int i = 0; i < id.length; i++) {
				Menus m = new Menus();
				m.setId(Integer.parseInt(id[i]));
				roles.getMenus().add(m);
			}
		}
	}
}
