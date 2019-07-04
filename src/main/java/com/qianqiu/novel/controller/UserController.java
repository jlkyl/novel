package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Pages;
import com.qianqiu.novel.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("usc")
public class UserController {

    @Resource
    UsersService us;
    /*@RequestMapping("page")
    public String page(Integer pageNum, Integer pageSize, Model m) {
        m.addAttribute("list", us.page(pageNum, pageSize));
        m.addAttribute("pageNum", pageNum);
        return "page";
    }*/
    @RequestMapping("pageList")
    @ResponseBody
    public Pages pageList(Integer page, Integer rows) {
        return us.querypage(page,rows);
    }
}
