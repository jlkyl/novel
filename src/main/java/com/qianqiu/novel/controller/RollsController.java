package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Books;
import com.qianqiu.novel.entity.Rolls;
import com.qianqiu.novel.service.BooksService;
import com.qianqiu.novel.service.RollsService;
import com.qianqiu.novel.utils.FileUtil;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("rolls")
public class RollsController {

	@Resource
	RollsService service;

	@Resource
	BooksService booksService;

	@RequestMapping("querybookid")
	public String querybookid(String bookname,HttpSession session){
		Books b=booksService.findByName(bookname);
		session.setAttribute("BOOK",b);
		System.out.println("显示书籍信息——b："+b);
		return "redirect:/chapteradd";
	}




	@RequestMapping("addRoll")
	@ResponseBody
	public boolean addRoll(Rolls rolls,HttpSession session){
		Books books=(Books) session.getAttribute("BOOK");
		Integer bookid=books.getBookid();
		String bookname=booksService.querybyId(bookid).getBookname();
		//FileUtil.write("D:\\Study\\Writerread\\",bookname);
		//rolls.setUrl(bookname+"\\"+rolls.getRollname());
		rolls.setBookid(bookid);
		rolls.setUrl(bookname+"\\"+rolls.getRollname());
		Rolls re=service.queryRollname(rolls.getRollname(),bookid);
		if(re!=null){
			return false;
		}else{
			service.add(rolls);
			FileUtil.File(bookname+"\\"+rolls.getRollname());
			return true;
		}

	}

	@RequestMapping("queryRoll")
	@ResponseBody
	public List<Rolls> queryRoll(HttpSession session){
		Books books=(Books) session.getAttribute("BOOK");
		Integer bookid=books.getBookid();
		return service.findByBookid(bookid);
	}

	@RequestMapping("updvip")
	@ResponseBody
	public Integer updvip(Integer isvip,Integer rollid){
		return service.updvip(isvip, rollid);
	}
}
