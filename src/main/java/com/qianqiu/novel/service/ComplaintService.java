package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.IComplaintDAO;
import com.qianqiu.novel.entity.Complaint;
import com.qianqiu.novel.entity.Pages;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ComplaintService {
    @Resource
    IComplaintDAO dao;

    public Integer add(Complaint complaint){
        return dao.add(complaint);
    }

    public Integer update(Complaint complaint){
        return dao.update(complaint);
    }
    public Pages query(Integer state, Integer userid, Integer pageNum, Integer pageSize){
        Pages pages = new Pages();
        if(pageNum == null || pageSize == null) {
            pages.setRows(dao.query(state, userid, null, null));
        }else{
            pages.setTotal(dao.getCount(state, userid));
            pages.setRows(dao.query(state, userid, (pageNum - 1) * pageSize, pageSize));
        }
        return pages;
    }
}
