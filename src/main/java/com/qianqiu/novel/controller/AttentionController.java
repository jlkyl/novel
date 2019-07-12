package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Attention;
import com.qianqiu.novel.entity.Users;
import com.qianqiu.novel.service.AttentionService;
import com.qianqiu.novel.utils.MyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("att")
public class AttentionController {

    @Resource
    AttentionService service;


    @RequestMapping(value="query")
    @ResponseBody
    public List<Map<String,Object>> query(HttpSession session){
        return service.query(MyUtil.getuserid(session));
    }


    @RequestMapping(value="queryas")
    @ResponseBody
    public List<Map<String,Object>> queryas(HttpSession session){
        return service.queryas(MyUtil.getuserid(session));
    }


    @RequestMapping(value="queryasd")
    @ResponseBody
    public List<Map<String,Object>> queryasd(HttpSession session){
        return service.queryasd(MyUtil.getuserid(session));
    }

    @RequestMapping(value="add")
    @ResponseBody
    public Integer add(Attention a,HttpSession session){
        a.setUserid(MyUtil.getuserid(session));

          Integer  rs = service.add(a);

        return rs;
    }

    @RequestMapping(value="del")
    @ResponseBody
    public Integer del(Integer attentuser,HttpSession session){

        Integer at = service.del(attentuser, MyUtil.getuserid(session));
        System.out.println(at);
        return at;
    }
}
