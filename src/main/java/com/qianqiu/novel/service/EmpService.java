package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.EmpDAO;
import com.qianqiu.novel.entity.Emps;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class EmpService {

    @Resource
    EmpDAO dao;

    public Emps loginB(String uname,String pwd){
        return dao.loginB(uname,pwd);
    }

    public List<Emps> queryEmp(){
        return dao.queryEmp();
    }

    public int updateP(Emps e){
         return dao.updateP(e);
    }

    public int add(Emps e){
        return dao.add(e);
    }

    public int update(Emps e){
        return dao.update(e);
    }

    public List<Map<String,Object>> queryAll(){
        return dao.queryAll();
    }

    public int US(Emps e){
        return dao.US(e);
    }
}
