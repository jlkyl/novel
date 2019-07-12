package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Expnses;
import com.qianqiu.novel.entity.Pages;
import com.qianqiu.novel.entity.Users;
import com.qianqiu.novel.service.AttentionService;
import com.qianqiu.novel.service.BooktypeService;
import com.qianqiu.novel.service.ExpnsesService;
import com.qianqiu.novel.service.UsersService;
import com.qianqiu.novel.utils.MyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UsersController {
    @Resource
    UsersService service;
    @Resource
    ExpnsesService eservice;

    @Resource
    AttentionService aservice;

    @Resource
    BooktypeService bservice;


    @RequestMapping(value="queryst")
    public String queryst(HttpSession session,Model u,Integer attentuser){
        List<Map<String,Object>> list = aservice.queryst(MyUtil.getuserid(session),attentuser);
        List<Map<String,Object>> qd = bservice.querybytu();
        u.addAttribute("list",list);
        u.addAttribute("qd",qd);
        return "homepage";
    }

    @RequestMapping("pageList")
    @ResponseBody
    public Pages pageList(Integer page, Integer rows,String username,String phone,Integer author) {
        return service.querypage(page,rows,username,phone,author);
    }


    @RequestMapping(value="querys")
    public String querys(Model u){
        List<Map<Users,Object>> list = service.querys();
        System.out.println(list);
        u.addAttribute("list",list);
        return "author_details";
    }

    @RequestMapping(value="recharge")
    public String recharge(Integer money, HttpSession session){

        Users user=(Users) session.getAttribute("user");
        //添加充值记录
        Expnses e = new Expnses();
        e.setUserid(user.getUserid());
        e.setExptypeid(1);
        e.setExpmoney(money);
        eservice.add(e);
        //修改用户信息
        Integer ti = user.getTicked();
        Integer t = ti+(int)Math.floor(money/10);
        Integer mo=user.getMoney();
        Integer i=money*100+mo;
        service.update(i,t,user.getUserid());
        //修改vip等级
        Integer vip = 0;
        Integer expmoney = eservice.queryMoney(user.getUserid());
        if(expmoney>10 && expmoney <100){
            vip = 1;
        }else if(expmoney>=100 && expmoney<300){
            vip = 2;
        }else if(expmoney>=300 && expmoney<500){
            vip = 3;
        }else if(expmoney>=500){
            vip = 4;
        }
        service.updVip(vip,user.getUserid());
        //更新session
        session.setAttribute("user",service.findByid(user.getUserid()));

        return "redirect:/close";
    }
    @RequestMapping(value="addMoney")
    @ResponseBody
    public Integer addMoney(Integer money,Integer ticket,HttpSession session){
        Users user=(Users) session.getAttribute("user");
        Integer res = 0;
        if(money!=null) {
            res = service.update(user.getMoney() + money, null, user.getUserid());
        }else if(ticket!=null){
            res = service.update(null,user.getTicked() + ticket,user.getUserid());
        }
        session.setAttribute("user",service.findByid(user.getUserid()));
        return res;
    }
    @RequestMapping(value="buyrecord")
    @ResponseBody
    public Integer buyrecord(Integer bookid,Integer[] chapid,Integer[] expmoney,Integer money, HttpSession session){
        Integer res = eservice.buyrecord(MyUtil.getuserid(session),bookid,chapid,expmoney);
        Users user=(Users) session.getAttribute("user");
        service.update(user.getMoney()-money,null,user.getUserid());
        session.setAttribute("user",service.findByid(user.getUserid()));
        return res;
    }
    @RequestMapping(value="give")
    @ResponseBody
    public Integer give(Expnses e, HttpSession session){
        e.setExptypeid(2);
        e.setUserid(MyUtil.getuserid(session));
        Integer res = eservice.add(e);
        Users user=(Users) session.getAttribute("user");
        service.update(user.getMoney()-e.getExpmoney(),null,user.getUserid());
        session.setAttribute("user",service.findByid(user.getUserid()));
        return res;
    }
    @RequestMapping(value="vote")
    @ResponseBody
    public Integer vote(Integer bookid,Integer nums,HttpSession session){
        Integer res = eservice.vote(MyUtil.getuserid(session),bookid,nums);
        Users user=(Users) session.getAttribute("user");
        service.update(null,user.getTicked()-nums,user.getUserid());
        session.setAttribute("user",service.findByid(user.getUserid()));
        return res;
    }
    @RequestMapping(value="getTicket")
    @ResponseBody
    public Map<String,Object> getTicket(HttpSession session){
        return service.getTicket(MyUtil.getuserid(session));
    }
    @RequestMapping(value="ckSession")
    @ResponseBody
    public Users ckSession(HttpSession session){
        Users users = (Users)session.getAttribute("user");
        if (null==users){
            return null;
        }
        return users;
    }
    @RequestMapping(value="queryIncome")
    @ResponseBody
    public Map<String,Object> queryIncome(HttpSession session){
        return service.queryIncome(MyUtil.getuserid(session));
    }
    @RequestMapping(value="getIncome")
    @ResponseBody
    public Integer getIncome(Expnses e,HttpSession session){
        e.setUserid(MyUtil.getuserid(session));
        e.setExptypeid(4);
        return eservice.add(e);
    }
}