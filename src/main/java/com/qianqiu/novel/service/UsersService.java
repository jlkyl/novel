package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.IUsersDAO;
import com.qianqiu.novel.entity.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    public int updateUser(Users users){
        return u.updateUser(users);
    }

    public int updhead(Users users) {
        return u.updhead(users);
    }
    public int updUser(Users users){return u.updUser(users);}
    //查询手机号
    public Integer Surephone(String phone){return u.Surephone(phone);}
    //查询邮箱
    public Integer Sureemail(String email){return u.Sureemail(email);}
    //查询笔名
    public Integer Surepen(String username){return u.Surepen(username);}
    //查询真实姓名
    public Integer Surerealname(String username){return u.Surepen(username);}
    //查询身份证
    public Integer Sureidcard(String username){return u.Sureidcard(username);}

    public List<Map<String,Object>> findAuthor(Integer siteid){
        return u.findAuthor(siteid);
    }
    public Integer updAuthor(Users users){
        return u.updAuthor(users);
    }
    public List<Users> query(){
        return u.query();
    }
    public List<Users> querys(){
        return u.querys();
    }
    public int update(Integer money,Integer ticket,Integer userid){
        return u.update(money,ticket,userid);
    }
    public Users queryAuthorById(Integer userid){
        return u.queryAuthorById(userid);
    }
}
