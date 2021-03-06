package com.qianqiu.novel.service;

import com.qianqiu.novel.dao.IUsersDAO;
import com.qianqiu.novel.entity.Pages;
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
    public Pages querypage(Integer pageNum, Integer pageSize,String username,String phone,Integer author){
        Pages pages = new Pages();
        pages.setRows(u.listAll((pageNum-1)*pageSize, pageSize,username,phone,author));
        pages.setTotal(u.getCount(username, phone, author));
        return pages;
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

    public List<Map<Users,Object>> querys(Integer userid){
        return u.querys(userid);
    }
    public int update(Integer money,Integer ticket,Integer userid){
        return u.update(money,ticket,userid);
    }
    public Users findByid(Integer userid){
        return u.findByid(userid);
    }
    public Map<String,Object> getTicket(Integer userid){
        return u.getTicket(userid);
    }
    public Integer updVip(Integer vip,Integer userid){
        return u.updVip(vip, userid);
    }
    public Users queryAuthorById(Integer userid){
        return u.queryAuthorById(userid);
    }
    public Map<String,Object> queryIncome(Integer userid){
        return u.queryIncome(userid);
    }
}
