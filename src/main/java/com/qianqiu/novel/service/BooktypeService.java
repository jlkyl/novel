package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.BooktypeDao;
import com.qianqiu.novel.entity.Booktype;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BooktypeService {

    @Resource
    BooktypeDao btd;

    public List<Booktype> queryAll(){
        return btd.queryAll();
    }

    public Booktype queryById(Integer typeid){
        return btd.queryById(typeid);
    }

    public int add(Booktype bt){
        return btd.add(bt);
    }

    public int update(Booktype bt){
        return btd.update(bt);
    }

    public int del(Integer typeid){
        return btd.del(typeid);
    }
}