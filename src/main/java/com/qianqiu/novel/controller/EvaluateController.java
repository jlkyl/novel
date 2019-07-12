package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Evaluate;
import com.qianqiu.novel.service.EvaluateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("eva")
public class EvaluateController {

    @Resource
    EvaluateService es;

    @RequestMapping("queryEvaluate")
    @ResponseBody
    public List<Map<String,Object>> queryEvaluat(Integer userid,Integer bookid, Model m){
        m.addAttribute("elist",es.queryEvaluate(userid,bookid));
        return es.queryEvaluate(userid,bookid);
    }

    @RequestMapping("queryById")
    public String queryById(Integer bookid,Integer evaid, Model m){
        m.addAttribute("elist",es.queryEvaluate(bookid,evaid));
        m.addAttribute("evaid",evaid);
        return "remark";
    }

    @RequestMapping("add")
    @ResponseBody
    public Integer add(Evaluate e){
        return es.add(e);
    }
}
