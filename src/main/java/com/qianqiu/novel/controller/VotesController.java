package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Votes;
import com.qianqiu.novel.service.VotesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("vote")
public class VotesController {

    @Resource
    VotesService vs;

    @RequestMapping("queryVoteById")
    @ResponseBody
    public List<Map<String,Object>> queryVoteById(Integer userid){
        return vs.queryVoteById(userid);
    }
}
