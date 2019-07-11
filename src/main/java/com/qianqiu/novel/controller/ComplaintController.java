package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Complaint;
import com.qianqiu.novel.entity.Pages;
import com.qianqiu.novel.service.ComplaintService;
import com.qianqiu.novel.utils.MyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("complaint")
public class ComplaintController {
    @Resource
    ComplaintService service;

    @RequestMapping("add")
    @ResponseBody
    public Integer add(Complaint complaint, HttpSession session){
        complaint.setUserid(MyUtil.getuserid(session));
        return service.add(complaint);
    }
    @RequestMapping("upd")
    @ResponseBody
    public Integer upd(Complaint complaint,HttpSession session){
        complaint.setOperateeid(MyUtil.getempid(session));
        return service.update(complaint);
    }
    @RequestMapping("query")
    @ResponseBody
    public Pages query(Integer state,Integer userid,Integer page,Integer rows){
        return service.query(state, userid, page, rows);
    }
}
