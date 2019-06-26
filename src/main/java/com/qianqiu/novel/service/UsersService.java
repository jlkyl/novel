package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.IUsersDAO;
import com.qianqiu.novel.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UsersService {

    @Resource
    IUsersDAO u;

    public Users unamelogin(String username,String password){
        return u.unamelogin(username,password);
    }
    public Users emaillogin(String username,String password){
        return u.emaillogin(username,password);
    }
    public Users plogin(String username,String password){
        return u.plogin(username,password);
    }

    public Users yzmlogin(String phone){
        return u.yzmlogin(phone);
    }
    public int addlogin(Users users){
      return u.addlogin(users);
    }
    public Users findByPen(String pen){
        return u.findByPen(pen);
    }

}
