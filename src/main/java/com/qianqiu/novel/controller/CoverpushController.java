package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Coverpush;
import com.qianqiu.novel.service.CoverpushService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import com.qianqiu.novel.utils.FileUtil;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("Co")
public class CoverpushController {

    @Resource
    CoverpushService service;

    @RequestMapping("query")
    @ResponseBody
    public List<Coverpush> query() {
        return service.query();
    }
    @RequestMapping("queryFT")

    public String queryFT(Model m) {
        System.out.println("sssssssssssssssssssssssss");
        m.addAttribute("ps",service.queryFT());
        System.out.println("fffffffffffffffffff");
        return "coverpush";
    }
    @RequestMapping("queryAll")
    @ResponseBody
    public Object queryAll(Integer page, Integer rows) {
        return service.queryPage(page,rows);
    }

    @RequestMapping(value="add")
    @ResponseBody
    public String add(Coverpush c, MultipartFile[] file) throws IOException {

        c.setCover(FileUtil.fileUpload(file));
        c.setOperatedate(new Date());
        Integer rs = service.add(c);
        System.out.println(rs);
        return "back/notices";
    }
    @RequestMapping(value="update")
    @ResponseBody
    public String update(Coverpush c, MultipartFile[] file) throws IOException {
        System.out.println(file.length);
        System.out.println(c);

        if(null!=file) {
            c.setCover(FileUtil.fileUpload(file));
        }else if(null == file){
            c.setCover(c.cover);

        }
        c.setOperatedate(new Date());
        Integer rs = service.update(c);
        System.out.println(rs);
        return "redirect:query";
    }
    @RequestMapping(value="del")
    @ResponseBody
    public String del(Integer pushid){
        Integer rs = service.del(pushid);
        System.out.println(rs);
        return "redirect:query";
    }
    @RequestMapping(value="queryById")
    public String queryById(Integer pushid){
        Coverpush n = service.queryById(pushid);
        System.out.println(pushid);
        return "update";
    }

}
