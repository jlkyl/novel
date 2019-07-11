package com.qianqiu.novel.controller;

import com.qianqiu.novel.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("rep")
public class ReplyController {

    @Resource
    ReplyService rs;

    @RequestMapping("queryReply")
    @ResponseBody
    public List<Map<String,Object>> queryReply(Integer userid){
        return rs.queryReply(userid);
    }
}
