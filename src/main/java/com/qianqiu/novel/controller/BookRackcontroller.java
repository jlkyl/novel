package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.BookRack;
import com.qianqiu.novel.service.BookRackService;
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
@RequestMapping("bor")
public class BookRackcontroller {
    @Resource
    BookRackService brs;

    @RequestMapping("queryBr")
    @ResponseBody
    public List<Map<String, Object>> queryBr(Model m, HttpSession session,String km){
        if(km==""){
            km=null;
        }
        return brs.queryBr(MyUtil.getuserid(session),km);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(Integer rackid){
        brs.delete(rackid);
        return "1";
    }
    @RequestMapping("add")
    @ResponseBody
    public Integer add(BookRack br,HttpSession session) {
        br.setUserid(MyUtil.getuserid(session));
        return brs.add(br);
    }
    @RequestMapping("queryF")
    @ResponseBody
    public Integer queryF(Integer bookid,HttpSession session){
        Integer userid = MyUtil.getuserid(session);
        if(userid == null){
            return -1;
        }else{
            List<BookRack> list = brs.queryF(userid, bookid);
            return list.size();
        }
    }
}

