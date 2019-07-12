package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.ReplyDao;
import com.qianqiu.novel.entity.Reply;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ReplyService {

    @Resource
    ReplyDao rd;

    public List<Map<String,Object>> queryReply(Integer evaid,Integer userid){
        return rd.queryReply(evaid,userid);
    }

    public int add(Reply r){
        return rd.add(r);
    }
}
