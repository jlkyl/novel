package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Users;
import com.qianqiu.novel.service.UsersService;
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
    public String upload(MultipartFile head, HttpSession session) throws IOException {
        System.out.println(head);
        String originalFilename = head.getOriginalFilename();

        System.out.println(originalFilename.equals(""));
        System.out.println(originalFilename != null);
        // 判断是否有文件
        if (originalFilename != null) {
            // 1.保存路径
            String savePath = "D:/upload";

            // 2.重命名
            UUID randomUUID = UUID.randomUUID();
            String newFileName = randomUUID.toString() + "_" + originalFilename;
            String saveFilePath = savePath + "/" + newFileName;
            Users user = new Users();
            user.setUserid(((Users)session.getAttribute("user")).getUserid());
            user.setHead(saveFilePath);
            us.updhead(user);
            // 3.保存文件:另存为
            head.transferTo(new File(saveFilePath));

        }
        return "1";
    }
}
