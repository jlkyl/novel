package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Books;
import com.qianqiu.novel.entity.Chapters;
import com.qianqiu.novel.entity.Users;
import com.qianqiu.novel.service.BooksService;
import com.qianqiu.novel.utils.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("books")
public class BooksController {

	@Resource
	BooksService service;

	@RequestMapping("addBooks")
	@ResponseBody
	public boolean addBooks(Books books, HttpSession session){

		String name=books.getBookname();
		Users u=(Users) session.getAttribute("user");
		u.getUserid();
		String pen=u.getPen();
		String cover= FileUtil.createImage(name,pen);

		books.setCover(cover);
        System.out.println("图片路径："+books.getCover());
		books.setUserid(u.getUserid());
		books.setState(0);//默认连载0
		books.setPutaway(0);//默认状态为0，未上架
		books.setClicknum(0);//默认点击数量0
		Books b=service.findByName(name);
		if(b!=null){
			return false;
		}else {
			Integer i=service.add(books);
			return true;
		}
	}

	@RequestMapping("booksAll")
	@ResponseBody
	public List<Map<String,Object>> booksAll(Integer userid,HttpSession session){
		Users u=(Users) session.getAttribute("user");

		return service.booksAll(u.getUserid());
	}

	@RequestMapping("queryTime")
	@ResponseBody
	public Chapters queryTime(Integer bookid){

        Chapters c=service.queryTime(bookid);
        System.out.println("查询的最新章节："+c.getChaptername()+c.getUpdatetime());
		return c;
	}

	@RequestMapping("updBookputaway")
    @ResponseBody
	public boolean updBookputaway(Integer bookid){
	    service.updBookstate(bookid);
	    return true;
    }

	@RequestMapping("updPutaway")
	@ResponseBody
    public Integer updPutaway(Integer putaway,Integer bookid){
		return service.updPutaway(putaway, bookid);
	}

}
