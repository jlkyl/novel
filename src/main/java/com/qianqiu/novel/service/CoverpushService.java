package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.CoverpushDao;
import com.qianqiu.novel.entity.Coverpush;
import com.qianqiu.novel.entity.Pages;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CoverpushService {
    @Resource
    CoverpushDao dao;
    public List<Coverpush> query(){
        return dao.query();
    }
    public int add(Coverpush c){
        return dao.add(c);
    }
    public int update(Coverpush c){
        return dao.update(c);
    }
    public int del(Integer pushid){
        return dao.del(pushid);
    }

    public Coverpush queryById(Integer pushid){
        return dao.queryById(pushid);
    }
    public Pages queryPage(Integer offset, Integer pageSize){
        Pages p = new Pages();
        p.setRows(dao.queryPage((offset-1)*pageSize,pageSize));
        p.setTotal(dao.getCounts());
        return p;
    }
    public List<Coverpush> queryFT(){
        return dao.queryFT();
    }
}
