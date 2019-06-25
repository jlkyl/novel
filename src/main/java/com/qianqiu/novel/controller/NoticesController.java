package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Notices;
import com.qianqiu.novel.entity.Pages;
import com.qianqiu.novel.service.NoticesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("Notices")
public class NoticesController {

    @Resource
    NoticesService service;

    @RequestMapping("topage")
    public String topage(){
        return "back/notices";
    }


    @RequestMapping(value="query")
    @ResponseBody
    public List<Notices> query(){
        List<Notices> query = service.query();
        return query;
    }

    @RequestMapping("queryAll")
    @ResponseBody
    public Object queryAll(Integer page, Integer rows) {
//        System.out.println(offset+pageSize);
//        System.out.println(service.queryPage(offset,pageSize));
        return service.queryPage(page,rows);
    }

    @RequestMapping(value="add")
    @ResponseBody
    public String add(Notices n){
        n.setOpeartedate(new Date());
        Integer rs = service.add(n);
        System.out.println(rs);
        return "back/notices";
    }
    @RequestMapping(value="update")
    @ResponseBody
    public String update(Notices n){
        n.setOpeartedate(new Date());
        Integer rs = service.update(n);
        System.out.println(rs);
        return "redirect:query";
    }
    @RequestMapping(value="del")
    @ResponseBody
    public String del(Integer noticeid){
        Integer rs = service.del(noticeid);
        System.out.println(rs);
        return "redirect:query";
    }
    @RequestMapping(value="queryById")
    public String queryById(Integer noticeid){
        Notices n = service.queryById(noticeid);
        System.out.println(n);
        return "update";
    }

}