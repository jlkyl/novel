package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Emps;
import com.qianqiu.novel.entity.Labels;
import com.qianqiu.novel.entity.Pages;
import com.qianqiu.novel.service.LabelsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class LabelsController {
    @Resource
    LabelsService service;

    @RequestMapping("queryAll")
   public List<Labels> queryAll(){
        return service.queryAll();
    }

    @RequestMapping("del")
    public int del(Integer labelid){
        return service.del(labelid);
    }
    @RequestMapping("add")
    public int add(Labels l, HttpSession session){
        Integer operateeid = ((Emps)session.getAttribute("emps")).getEmpid();
        l.setOperateeid(operateeid);
        return service.add(l);
    }

    @RequestMapping("update")
    public int update(Labels l,HttpSession session){
        Integer operateeid = ((Emps)session.getAttribute("emps")).getEmpid();
        l.setOperateeid(operateeid);
        return service.update(l);
    }

    @RequestMapping("pageList")
    public Pages pageList(Integer page, Integer rows) {
        return service.querypage(page,rows);
    }
}
