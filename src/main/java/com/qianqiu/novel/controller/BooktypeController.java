package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Booktype;
import com.qianqiu.novel.entity.Emps;
import com.qianqiu.novel.service.BooktypeService;
import com.qianqiu.novel.service.EmpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("empqueryAll")
    @ResponseBody
    public List<Emps> queryEmp(){
        return es.queryEmp();
    }

    @RequestMapping("btadd")
    public String btadd(Booktype bt){
        bts.add(bt);
        return "redirect:/bt/btqueryAll";
    }

    @RequestMapping("btupdate")
    public String btupdate(Booktype bt){
        bts.update(bt);
        return "redirect:/bt/btqueryAll";
    }

    @RequestMapping("btdel")
    public String btdel(Integer typeids){
        bts.del(typeids);
        return "redirect:/bt/btqueryAll";
    }
}
