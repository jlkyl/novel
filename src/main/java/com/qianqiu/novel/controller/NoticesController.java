package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Notices;
import com.qianqiu.novel.service.NoticesService;
import com.qianqiu.novel.utils.MyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("Notices")
public class NoticesController {

    @Resource
    NoticesService service;

    @RequestMapping(value="query")
    public String query(Model u){
        List<Notices> list = service.query();
        u.addAttribute("list",list);
        return "notice";
    }

    @RequestMapping("queryAll")
    @ResponseBody
    public Object queryAll(Integer page, Integer rows) {
        return service.queryPage(page,rows);
    }

    @RequestMapping(value="add")
    @ResponseBody
    public String add(Notices n, HttpSession session){
        n.setOpeartedate(new Date());
        n.setOperateeid(MyUtil.getempid(session));
        Integer rs = service.add(n);
        System.out.println(rs);
        return "back/notices";
    }
    @RequestMapping(value="update")
    @ResponseBody
    public String update(Notices n, HttpSession session){
        n.setOpeartedate(new Date());
        n.setOperateeid(MyUtil.getempid(session));
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
    @RequestMapping(value="queryByIds")
    public String queryByIds(Integer noticeid,Model u){
        Notices n = service.queryByIds(noticeid);
        System.out.println(n);
        u.addAttribute("ns",n);
        return "Notice_details";
    }
}
