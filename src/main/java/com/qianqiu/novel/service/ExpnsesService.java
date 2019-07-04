package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.ExpnsesDao;
import com.qianqiu.novel.entity.Expnses;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExpnsesService {
    @Resource
    ExpnsesDao dao;

    public List<Expnses> query(){
        return dao.query();
    }

    public int add(Expnses e){
        return dao.add(e);
    }

    public Expnses queryById(Integer expid){
        return dao.queryById(expid);
    }
}
