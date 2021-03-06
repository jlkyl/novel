package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.BookRackDAO;
import com.qianqiu.novel.entity.BookRack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BookRackService {
    @Resource
    BookRackDAO brd;

    public List<Map<String ,Object >> queryBr(Integer userid,String km){
        return brd.queryBr(userid,km);
    }
    public int delete(Integer rackid){
        return brd.delete(rackid);
    }
    public Integer add(BookRack br){
        return brd.add(br);
    }
    public List<BookRack> queryF(Integer userid,Integer bookid){
       return brd.queryF(userid,bookid);
    }
    public Integer queryCount(Integer userid){
        return brd.queryCount(userid);
    }
}
