package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Expnses;
import com.qianqiu.novel.service.ExpnsesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("expn")
public class ExpnsesController {

    @Resource
    ExpnsesService service;

    @RequestMapping(value="querys")
    @ResponseBody
    public List<Expnses> querys() {
        return service.query();
    }

    @RequestMapping(value="queryById")
    public String queryById(Integer expid, Model u){
        Expnses n = service.queryById(expid);
        System.out.println(n);
        u.addAttribute("list",n);
        return "topup";
    }
}
