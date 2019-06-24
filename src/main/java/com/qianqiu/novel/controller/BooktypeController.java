package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Booktype;
import com.qianqiu.novel.service.BooktypeService;
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

    @RequestMapping("btqueryAll")
    @ResponseBody
    public List<Booktype> btqueryAll(){
       System.out.println("查询");
       return bts.queryAll();
    }

    @RequestMapping("btqueryId")
    public String btqueryId(Model bt,Integer typeid){
        bt.addAttribute("bt", bts.queryById(typeid));
        System.out.println("查询！！！！！："+bts.queryById(typeid));
        return "back/bt_update";
    }

    @RequestMapping("btadd")
    public String btadd(Booktype bt){
        System.out.println("btadd");
        bts.add(bt);
        System.out.println(bt);
        return "redirect:/bt/jump";
    }

    @RequestMapping("btupdate")
    public String btupdate(Booktype bt){
        bts.update(bt);
        return "redirect:/bt/btqueryAll";
    }

    @RequestMapping("btdel")
    public String btdel(Integer typeids){
        System.out.println("btdel");
        bts.del(typeids);
        return "redirect:/bt/btqueryAll";
    }
}
