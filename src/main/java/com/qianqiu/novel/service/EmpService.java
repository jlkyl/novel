package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.EmpDAO;
import com.qianqiu.novel.entity.Emps;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class EmpService {

    @Resource
    EmpDAO dao;

    public Emps loginB(String uname,String pwd){
        return dao.loginB(uname,pwd);
    }
}
