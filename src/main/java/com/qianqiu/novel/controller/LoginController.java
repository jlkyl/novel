package com.qianqiu.novel.controller;

import com.miaodiyun.httpApiDemo.IndustrySMS;
import com.qianqiu.novel.entity.Users;
import com.qianqiu.novel.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("Login")
@ResponseBody
public class LoginController {

    @Resource
    UsersService u;
    Map<String,String> map=new HashMap<String,String>();

    @RequestMapping("unameLogin")
    public boolean unameLogin(String username, String password, HttpSession session) {
        String em="^\\w+([-+.])*@\\w+([-.]\\+)*\\.\\w+([-.]\\w+)*$";
        String ph="^[1][3578]\\d{9}$";
        if(username.matches(em)){
            Users user=u.emaillogin(username,password);
            session.setAttribute("name", user);
        }else if(username.matches(ph)){
            Users user=u.plogin(username,password);
            session.setAttribute("name", user);
        }else {
            Users user=u.unamelogin(username,password);
            session.setAttribute("name", user);
        }
        Object name=session.getAttribute("name");
        if(name!=null){
            return true;
        }else{
            return false;
        }
    }
    @RequestMapping("phoneLogin")
    public String phoneLogin(String phone){
        Users users=u.yzmlogin(phone);
        System.out.println(phone);
        System.out.println(users);
        if(users!=null){
            //String returnCode= IndustrySMS.execute(phone);
            String returnCode="110022";
            map.put(phone,returnCode);
            return returnCode;
        }else{
            return "";
        }
    }
    @RequestMapping("yzmLogin")
    public boolean yzmLogin(String phone,String yzm){
        System.out.println(map.get(phone));
        if(yzm.equals(map.get(phone))){
            return true;
        }else{
            return false;
        }
    }
    @RequestMapping("addlogin")
    public Integer addlogin(String phone,String password,String yzm){
        Users users=new Users();
        users.setPhone(phone);
        users.setPassword(password);

        //String returnCode= IndustrySMS.execute(phone);
        String returnCode="110033";
        map.put(phone,returnCode);
        if(yzm.equals(map.get(phone))){
           int i= u.addlogin(users);
            if(users ==null){
                return 1;
            }else{
                return -1;
            }
        }else{
            return 0;
        }

    }
    @RequestMapping("checkphone")
    public boolean checkphone(String phone){
        Users user=u.yzmlogin(phone);
        if(user ==null){
            return false;
        }
        else{
            return true;
        }
    }
}
