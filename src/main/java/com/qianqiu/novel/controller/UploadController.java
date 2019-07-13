package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Users;
import com.qianqiu.novel.service.UsersService;
import com.qianqiu.novel.utils.FileUtil;
import com.qianqiu.novel.utils.MyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("Upload")
public class UploadController {

    @Resource
    UsersService us;

    @RequestMapping("upload")
    @ResponseBody
    public String upload(MultipartFile[] head, HttpSession session) throws IOException {

        Users user = new Users();
        user.setUserid(MyUtil.getuserid(session));
        user.setHead(FileUtil.fileUpload(head));
        us.updhead(user);
        session.setAttribute("user",us.findByid(user.getUserid()));
        return "1";
    }
}
