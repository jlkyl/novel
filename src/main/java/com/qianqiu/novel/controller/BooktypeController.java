package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Booktype;
import com.qianqiu.novel.entity.Emps;
import com.qianqiu.novel.entity.Pages;
import com.qianqiu.novel.service.BooktypeService;
import com.qianqiu.novel.service.EmpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("bt")
public class BooktypeController {

    @Resource
    BooktypeService bts;

    @Resource
    EmpService es;

    @RequestMapping("btqueryAll")
    @ResponseBody
    public List<Map<String,Object>> btqueryAll(){
       return bts.queryAll();
    }

    @RequestMapping("pageList")
    @ResponseBody
    public Pages pageList(Integer page, Integer rows){
        return bts.querypage(page,rows);
    }

    @RequestMapping("empqueryAll")
    @ResponseBody
    public List<Emps> queryEmp(){
        return es.queryEmp();
    }

    @RequestMapping("btadd")
    public String btadd(Booktype bt, HttpSession session){
        bt.setOperateeid(((Emps)session.getAttribute("emps")).getEmpid());
        bts.add(bt);
        return "redirect:/bt/btqueryAll";
    }

    @RequestMapping("btupdate")
    public String btupdate(Booktype bt, HttpSession session){
        System.out.println("修改");
        bt.setOperateeid(((Emps)session.getAttribute("emps")).getEmpid());
        bts.update(bt);
        return "redirect:/bt/btqueryAll";
    }

    @RequestMapping("btdel")
    @ResponseBody
    public String btdel(Integer typeids){
        bts.del(typeids);
        return "1";
    }

    @RequestMapping("queryParentid")
    @ResponseBody
    public List<Map<String,Object>> queryParentall(){
    List<Map<String,Object>> b=bts.queryParentall();
    System.out.println("总分类："+b);
    return b;
    }

    @RequestMapping("queryByparent")
    @ResponseBody
    public List<Map<String,Object>> queryByparent(Integer typeid){
        List<Map<String,Object>> b=bts.queryByparentid(typeid);
        System.out.println("子分类："+b);
        return b;
    }
}
