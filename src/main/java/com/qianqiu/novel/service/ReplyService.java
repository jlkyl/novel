package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.ReplyDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ReplyService {

    @Resource
    ReplyDao rd;

    public List<Map<String,Object>> queryReply(Integer userid){
        return rd.queryReply(userid);
    }
}
