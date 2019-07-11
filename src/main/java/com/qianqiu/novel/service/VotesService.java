package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.VotesDao;
import com.qianqiu.novel.entity.Votes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class VotesService {

    @Resource
    VotesDao vd;

    public List<Map<String,Object>> queryVoteById(Integer userid){
        return vd.queryVoteById(userid);
    }
}
