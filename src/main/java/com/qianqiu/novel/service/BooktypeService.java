package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.BooktypeDao;
import com.qianqiu.novel.entity.Booktype;
import com.qianqiu.novel.entity.Pages;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BooktypeService {

    @Resource
    BooktypeDao btd;

    public List<Map<String,Object>> queryAll(){
        return btd.queryAll(null,null);
    }

    public Pages querypage(Integer pageNum, Integer pageSize){
        Pages pages = new Pages();
        pages.setRows(btd.queryAll((pageNum-1)*pageSize, pageSize));
        pages.setTotal(btd.getCount());
        return pages;
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

    public Booktype queryByName(String typename){
        return  btd.queryByName(typename);
    }

    public List<Map<String,Object>> queryParentall(){return btd.queryParentall();}

    public List<Map<String,Object>> queryByparentid(Integer parentid){return btd.queryByparentid(parentid);}

    public List<Map<String,Object>> querybytu(Integer userid){return btd.querybytu(userid);}
}
