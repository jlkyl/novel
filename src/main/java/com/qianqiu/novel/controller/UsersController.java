package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Expnses;
import com.qianqiu.novel.entity.Pages;
import com.qianqiu.novel.entity.Users;
import com.qianqiu.novel.service.ExpnsesService;
import com.qianqiu.novel.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("user")
public class UsersController {
    @Resource
    UsersService service;
    @Resource
    ExpnsesService eservice;

    @RequestMapping("pageList")
    @ResponseBody
    public Pages pageList(Integer page, Integer rows) {
        return service.querypage(page,rows);
    }

    @RequestMapping(value="query")
    @ResponseBody
    public List<Users> query() {

        return service.query();
    }

    @RequestMapping(value="querys")
    public String querys(Model u){
        List<Users> list = service.querys();
        u.addAttribute("list",list);
        return "author_details";
    }

    @RequestMapping(value="update")
    public String update(Integer money,Integer ticket, Model u, HttpSession session){
        Users user=(Users) session.getAttribute("user");
        Integer ti = user.getTicked();
        Integer t = ti+ticket;
        Integer mo=user.getMoney();
        Integer i=money+mo;
        Integer rs = service.update(i,t,user.getUserid());

        Expnses e = new Expnses();
        e.setUserid(user.getUserid());
        e.setExpmoney(Double.parseDouble(money.toString()));
        e.setExptime(new Date());
        System.out.println(e);
        Integer is = eservice.add(e);
        u.addAttribute("list", rs);
        return "personal";
    }
}