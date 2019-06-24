package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.LabelsDao;
import com.qianqiu.novel.entity.Labels;
import com.qianqiu.novel.entity.Pages;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LabelsService {
    @Resource
    LabelsDao dao;

    public List<Labels> queryAll(){
        return dao.queryAll();
    }

    public int add(Labels l){
        return dao.add(l);
    }

    public int update(Labels l){
        return dao.update(l);
    }

    public int del(Integer labelid){
        return dao.del(labelid);
    }

    public List<Labels> queryId(Integer labelid){
        return dao.queryId(labelid);
    }

    public Pages querypage(Integer pageNum, Integer pageSize){
        Pages pages = new Pages();
        pages.setRows(dao.findAll((pageNum-1)*pageSize, pageSize));
        pages.setTotal(dao.getCount());
        return pages;
    }
}
