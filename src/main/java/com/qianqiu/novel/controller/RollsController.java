package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Books;
import com.qianqiu.novel.entity.Rolls;
import com.qianqiu.novel.service.BooksService;
import com.qianqiu.novel.service.RollsService;
import com.qianqiu.novel.utils.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("rolls")
public class RollsController {

	@Resource
	RollsService service;

	@Resource
	BooksService booksService;

	@RequestMapping("querybookid")
	public String querybookid(String bookname,Model list){
		Books b=booksService.findByName(bookname);
		list.addAttribute("book",b);
		return "redirect:/chapteradd";
	}


	@RequestMapping("addRoll")
	@ResponseBody
	public boolean addRoll(Rolls rolls){

		String bookname=booksService.querybyId(rolls.getBookid()).getBookname();
		//FileUtil.write("D:\\Study\\Writerread\\",bookname);
		rolls.setUrl(bookname+"\\"+rolls.getRollname());
		Rolls r=service.queryRollname(rolls.getRollname(),rolls.getBookid());
		if(r!=null){
			service.add(rolls);
			return true;
		}else{
			return false;
		}

	}

	@RequestMapping("queryRoll")
	@ResponseBody
	public List<Rolls> queryRoll(Integer bookid){
		return service.findByBookid(bookid);
	}

	@RequestMapping("updvip")
	@ResponseBody
	public Integer updvip(Integer isvip,Integer rollid){
		return service.updvip(isvip, rollid);
	}
}
