package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.EvaluateDao;
import com.qianqiu.novel.entity.Evaluate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EvaluateService {

    @Resource
    EvaluateDao ed;

    public List<Map<String,Object>> queryEvaluate(Integer userid,Integer bookid){
        return ed.queryEvaluate(userid,bookid);
    }

    public List<Map<String,Object>> queryById(Integer bookid,Integer evaid){
        return ed.queryById(bookid,evaid);
    }

    public int add(Evaluate e){
        return ed.add(e);
    }
}
