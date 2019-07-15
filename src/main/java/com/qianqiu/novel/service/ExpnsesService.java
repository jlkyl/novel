package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.ExpnsesDao;
import com.qianqiu.novel.entity.Expnses;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ExpnsesService {
    @Resource
    ExpnsesDao dao;

    public List<Map<String,Object>> query(Integer exptypeid,Integer userid,Integer bookid){
        return dao.query(exptypeid,userid,bookid);
    }

    public int add(Expnses e){
        return dao.add(e);
    }
    public Integer buyrecord(Integer userid,Integer bookid,Integer[] chapid,Integer[] expmoney){
        return dao.buyrecord(userid, bookid, chapid, expmoney);
    }

    public List<Map<String,Object>> queryVote(Integer bookid,Integer userid){
        return dao.queryVote(bookid, userid);
    }

    public Integer vote(Integer userid,Integer bookid,Integer nums){
        return dao.vote(userid, bookid, nums);
    }

    public Integer queryMoney(Integer userid){
        return dao.queryMoney(userid);
    }

    public Object queryWeb(Integer year,Integer month){
        return dao.queryWeb(year,month);
    }
}
