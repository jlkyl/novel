package com.qianqiu.novel.controller;

import com.qianqiu.novel.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class WebController {
    @Resource
    BooktypeService bts;
    @Resource
    BooksService bs;
    @Resource
    WeekpushService ws;
    @Resource
    SiteService ss;

    @RequestMapping("index")
    public void index(Model model){
        model.addAttribute("typelist",bts.queryParentall());
        model.addAttribute("weekpush",ws.findAll(0,null));
        model.addAttribute("recommend1",bs.query(1,1,null));
        model.addAttribute("recommend2",bs.query(2,1,null));
        model.addAttribute("recommend3",bs.query(3,1,null));
        model.addAttribute("recommend4",bs.query(4,1,null));
        model.addAttribute("recommend5",bs.query(5,1,null));
        model.addAttribute("recommend6",bs.query(6,1,null));
        model.addAttribute("recommend7",bs.query(7,1,null));
        model.addAttribute("recommend8",bs.query(8,1,null));
        model.addAttribute("recommend9",bs.query(9,1,null));
        model.addAttribute("recommend10",bs.query(2,1,1));
        model.addAttribute("recommend11",bs.query(10,1,1));
        model.addAttribute("updates",bs.queryUpdate());
        model.addAttribute("authors",bs.queryAuthor());
        model.addAttribute("site",ss.findAll());
    }

    @RequestMapping("{name}")
    public void front(){}

    @RequestMapping("back/{name}")
    public void back(){}

}
