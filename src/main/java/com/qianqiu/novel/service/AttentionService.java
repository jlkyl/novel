package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.AttentionDao;
import com.qianqiu.novel.entity.Attention;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service

public class AttentionService {

    @Resource
    AttentionDao dao;

    public List<Map<String,Object>> query(Integer userid){
        return  dao.query(userid);
    }

    public List<Map<String,Object>> queryas(Integer userid){
        return  dao.queryas(userid);
    }
    public List<Map<String,Object>> queryasd(Integer userid){
        return  dao.queryasd(userid);
    }
    public List<Map<String,Object>> queryst(Integer userid,Integer attentuser){
        return  dao.queryst(userid,attentuser);
    }
    public int add(Attention a){
        return dao.add(a);
    }

    public Integer del(Integer attentuser,Integer userid){
        return dao.del(attentuser,userid);
    }
}
