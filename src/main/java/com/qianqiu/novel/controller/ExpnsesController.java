package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Expnses;
import com.qianqiu.novel.entity.Users;
import com.qianqiu.novel.service.ExpnsesService;
import com.qianqiu.novel.utils.MyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("expn")
public class ExpnsesController {

    @Resource
    ExpnsesService service;

    @RequestMapping(value="querys")
    @ResponseBody
    public List<Map<String,Object>> querys(Integer exptypeid, HttpSession session) {
        return service.query(exptypeid, MyUtil.getuserid(session),null);
    }
    @RequestMapping(value="queryVote")
    @ResponseBody
    public List<Map<String,Object>> queryVote(Integer bookid){
        return service.queryVote(bookid,null);
    }
    @RequestMapping(value="queryGive")
    @ResponseBody
    public List<Map<String,Object>> queryGive(Integer bookid){
        return service.query(2,null,bookid);
    }
    @RequestMapping(value="voteRecord")
    @ResponseBody
    public List<Map<String,Object>> voteRocord(HttpSession session){
        return service.queryVote(null,MyUtil.getuserid(session));
    }
    @RequestMapping(value="queryMoney")
    @ResponseBody
    public Integer queryMoney(HttpSession session){
        return service.queryMoney(MyUtil.getuserid(session));
    }
}
