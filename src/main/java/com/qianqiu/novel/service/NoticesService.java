package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.NoticesDao;
import com.qianqiu.novel.entity.Notices;
import com.qianqiu.novel.entity.Pages;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class NoticesService {

    @Resource
    NoticesDao dao;
    public List<Notices> query(){
       return dao.query();
    }
    public int add(Notices n){
        return dao.add(n);
    }
    public int update(Notices n){
        return dao.update(n);
    }
    public int del(Integer noticeid){
        return dao.del(noticeid);
    }
    public Notices queryByIds(Integer noticeid){
        return dao.queryByIds(noticeid);
    }
    public Pages queryPage(Integer offset, Integer pageSize){
       Pages p = new Pages();
       p.setRows(dao.queryPage((offset-1)*pageSize,pageSize));
       p.setTotal(dao.getCounts());
       return p;
    }


}
