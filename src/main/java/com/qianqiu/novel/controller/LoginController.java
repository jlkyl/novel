package com.qianqiu.novel.controller;

import com.miaodiyun.httpApiDemo.IndustrySMS;
import com.qianqiu.novel.entity.Users;
import com.qianqiu.novel.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

//@RestController
@Controller
@RequestMapping("Login")
public class LoginController {

    @Resource
    UsersService u;
    Map<String,String> map=new HashMap<String,String>();

    //账户（用户名，或邮箱，或手机号）登录
    @RequestMapping("unameLogin")
    @ResponseBody
    public boolean unameLogin(String username, String password, HttpSession session) {
        String em="^\\w+([-+.])*@\\w+([-.]\\+)*\\.\\w+([-.]\\w+)*$";
        String ph="^[1][345678]\\d{9}$";
        if(username.matches(em)){
            Users user=u.emaillogin(username,password);
            session.setAttribute("user", user);
        }else if(username.matches(ph)){
            Users user=u.plogin(username,password);
            session.setAttribute("user", user);
        }else {
            Users user=u.unamelogin(username,password);
            session.setAttribute("user", user);

        }
        Users name=(Users) session.getAttribute("user");
        if(name!=null){
            return true;
        }else{
            return false;
        }

    }
    //退出
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }
    //手机号发送验证码
    @RequestMapping("phoneLogin")
    @ResponseBody
    public String phoneLogin(String phone){
        Users users=u.yzmlogin(phone);
        if(users!=null){
            //String returnCode= IndustrySMS.execute(phone);
            String returnCode="110022";
            map.put(phone,returnCode);
            return returnCode;
        }else{
            return "";
        }
    }
    //验证码登录（检查手机号是否存在）
    @RequestMapping("yzmLogin")
    @ResponseBody
    public boolean yzmLogin(String phone,String yzm){
        System.out.println(map.get(phone));
        if(yzm.equals(map.get(phone))){
            return true;
        }else{
            return false;
        }
    }
    //前台注册
    @RequestMapping("addlogin")
    @ResponseBody
    public Integer addlogin(String phone,String password,String yzm){
        Users users=new Users();
        users.setPhone(phone);
        users.setPassword(password);
        users.setUsername("书友"+users.getPhone());
        users.setSex("男");
        users.setAuthor(0);
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
    //检查手机号是否存在
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
    //作者申请
    @RequestMapping("updUsers")
    @ResponseBody
    public boolean updUsers(Users users,HttpSession session){
        Users U=(Users)session.getAttribute("user");
        int id=U.getUserid();
        users.setUserid(id);
        int i= u.updUser(users);
        String phone=U.getPhone();
        String pwd=U.getPassword();
        Users user1=u.plogin(phone,pwd);
        session.setAttribute("user",user1);
        System.out.println("修改之后session存的值"+(Users)session.getAttribute("user"));
        if(i>0){
            return true;
        }else{
            return false;
        }
    }
    //读者身份验证（是读者还是作者）
    @RequestMapping("writerManager")
    public String writerManager(HttpSession session){

        //判断是否登录
        if(session.getAttribute("user")!=null){
            Users U=(Users)session.getAttribute("user");
            int author=U.getAuthor();
            //判断登录人是否是作者
            if(author==1){
                return "redirect:/authorWorks";
            }else{
                return "redirect:/writerperfect";
            }
        }else {
            return "redirect:/login";
        }
    }
    //检查手机号，邮箱，用户名是否唯一
    @RequestMapping("one")
    @ResponseBody
    public boolean one(String name){
        //邮箱
        String em="^\\w+([-+.])*@\\w+([-.]\\+)*\\.\\w+([-.]\\w+)*$";
        //手机号
        String ph="^[1][345678]\\d{9}$";
        //身份证
        String id="/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/";
        if(name.matches(em)){
            Integer i=u.Sureemail(name);
            System.out.println("邮箱："+name+i);
            if(i!=null){
                return true;
            }else{
                return false;
            }
        }else if(name.matches(ph)){
            Integer i=u.Surephone(name);
            System.out.println("手机号："+name+i);
            if(i!=null){
                return true;
            }else{
                return false;
            }
        }else if(name.matches(id)){
            Integer i=u.Sureidcard(name);
            System.out.println("身份证："+name+i);
            if(i!=null){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @RequestMapping("pen")
    @ResponseBody
    public boolean pen(String name){
        Integer i=u.Surepen(name);
        if(i!=null){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("realname")
    @ResponseBody
    public boolean realname(String name){
        Integer i=u.Surerealname(name);
        if(i!=null){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("userupdate")
    @ResponseBody
    public String updateUser(Users user) {
        System.out.println("修改");
        u.updateUser(user);
        System.out.println(user);
        return "1";
    }
}
