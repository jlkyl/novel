package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Books;
import com.qianqiu.novel.entity.Chapters;
import com.qianqiu.novel.service.BooksService;
import com.qianqiu.novel.entity.Rolls;
import com.qianqiu.novel.service.ChaptersService;
import com.qianqiu.novel.utils.AudioUtil;
import com.qianqiu.novel.utils.FileUtil;
import com.qianqiu.novel.service.RollsService;
import com.qianqiu.novel.utils.MyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("chapters")
public class ChaptersController {

	@Resource
	ChaptersService service;

	@Resource
	BooksService book;

	@RequestMapping("queryAll")
	public String queryAll(Model m,Integer bookid) {
		m.addAttribute("list", service.queryAll(bookid));

		m.addAttribute("query", service.queryChapter(bookid));
		return "lookBook";
	}

	@RequestMapping("queryId")
	public String queryId(Model m,Integer chapterId,HttpSession session){

		Map<String,Object> map = service.queryId(chapterId,MyUtil.getuserid(session));
		m.addAttribute("queryId",map);

		String read = 	FileUtil.read(map.get("url").toString());

		read = read.replaceAll("(\r\n|\n)","</p><p>");

		m.addAttribute("str",	read);

		Integer userid = MyUtil.getuserid(session);
		if(userid!=null) {
			if(service.getChapid(userid,chapterId,null)==null) {
				service.addBrowses(userid, chapterId);
			}else{
				service.updBrowses(userid,chapterId);
			}
		}
		return "lookBook";
	}

	@RequestMapping("getChapid")
	@ResponseBody
	public Integer getChapid(Integer bookid,HttpSession session){
		Integer chapid = null;
		Integer userid = MyUtil.getuserid(session);
		if(userid!=null) {
			chapid = service.getChapid(userid,null,bookid);
		}
		return chapid;
	}


	@Resource
	BooksService booksService;

	@Resource
	RollsService rollsService;

	@RequestMapping("addChapter")
	@ResponseBody
	public boolean addChapter(Chapters chapters,String txt, HttpSession session){
		Books books=(Books) session.getAttribute("BOOK");
		Integer bookid=books.getBookid();
		String bookname=booksService.querybyId(bookid).getBookname();
		Rolls rolls = rollsService.queryId(chapters.getRollid());
		String rollname=rolls.getRollname();
		Integer num=service.getOrder(bookid);
		chapters.setChapternum(num+1);
		chapters.setState(1);
		String chaptername=chapters.getChaptername();
		Chapters c=service.findByName(chapters.getChaptername(),bookid);
		chapters.setUrl(bookname+"\\"+rollname+"\\"+chaptername+".txt");
		if(c==null){
			service.add(chapters);
			FileUtil.write(bookname+"\\"+rollname+"\\"+chaptername+".txt",txt);
			return true;
		}else {
			return false;
		}


	}

	@RequestMapping("findAll")
	@ResponseBody
	public List<Chapters> findAll(HttpSession session,Integer state){
		Books books=(Books)session.getAttribute("BOOK");
		Integer bookid=books.getBookid();
		return service.findAll(bookid,state);
	}

	@RequestMapping("find001")
	@ResponseBody
	public List<Map<String,Object>> find001(HttpSession session){
		Books books=(Books)session.getAttribute("BOOK");
		List<Map<String,Object>> list=rollsService.findByBookid001(books.getBookid());
		if(list !=null){
			for(Map<String,Object> map:list){
					map.put("chapters",service.findName(Integer.parseInt(map.get("rollid").toString()),0));
			}
		}


		return list;
	}

	@RequestMapping("find0001")
	@ResponseBody
	public List<Map<String,Object>> find0001(HttpSession session){
		Books books=(Books)session.getAttribute("BOOK");
		List<Map<String,Object>> list=rollsService.findByBookid001(books.getBookid());
		if(list !=null){
			for(Map<String,Object> map:list){
				map.put("chapters",service.findName(Integer.parseInt(map.get("rollid").toString()),2));
			}
		}


		return list;
	}



	//草稿箱章节
	@RequestMapping("queryName")
	@ResponseBody
	public List<Chapters> queryName(Integer rollid){

		return service.findName(rollid,1);
	}
	//已发布章节
	@RequestMapping("queryName01")
	@ResponseBody
	public List<Chapters> queryName01(Integer rollid){

		return service.findName(rollid,0);
	}
	//回收站章节
	@RequestMapping("queryName02")
	@ResponseBody
	public List<Chapters> queryName02(Integer rollid){

		return service.findName(rollid,4);
	}

	@RequestMapping("queryTxt")
	@ResponseBody
	public String queryTxt(Integer chapterid){
		Chapters chapters=service.queryById(chapterid);
		String txt=chapters.getUrl();
		System.out.println(FileUtil.read(txt));
		return FileUtil.read(txt);
	}

	@RequestMapping("updChapterInfo")
	@ResponseBody
	public boolean updChapterInfo(String chaptername,Integer chapterid,Integer state,String txt,Integer wordnum,HttpSession session) {
		//书名
		Books books = (Books) session.getAttribute("BOOK");
		Integer bookid = books.getBookid();
		String bookname = booksService.querybyId(bookid).getBookname();

		Chapters chapters = service.queryById(chapterid);
		//卷名
		Rolls rolls = rollsService.queryId(chapters.getRollid());
		System.out.println("1111111111" + rolls);
		String rollname = rolls.getRollname();
		String oldChaptername = chapters.getUrl();
		FileUtil.upFileName(oldChaptername, bookname + "\\" + rollname + "\\" + chaptername + ".txt");
		FileUtil.write(bookname + "\\" + rollname + "\\" + chaptername + ".txt", txt);
		String urll = bookname + "\\" + rollname + "\\" + chaptername + ".txt";
		//如果要发布，则进行审核
		if (state == 0) {
			int a = AudioUtil.audioTxt(txt);
			int stat;
			if (a == 0) {
				stat = 0;//审核通过
			} else if (a == 2) {
				stat = 2;//进入复审
			} else {
				stat = 1;//审核不通过
			}
			Integer ii = service.updChapterInfo(chaptername, urll, stat, chapterid, wordnum);
			if (ii != null) {
				return true;
			} else {
				return false;
			}
		} else {
			Integer i = service.updChapterInfo(chaptername, urll, state, chapterid, wordnum);
			if (i != null) {
				return true;
			} else {
				return false;
			}
		}
	}

	@RequestMapping("updChapDel")
	@ResponseBody
	public boolean  updChapDel(Integer state,Integer chapterid){
		Integer i=service.updChapDel(state,chapterid);
		return true;
	}
	@RequestMapping("queryBrowses")
	@ResponseBody
	public List<Map<String,Object>> queryBrowses(HttpSession session){
		return service.queryBrowses(MyUtil.getuserid(session));
	}

	@RequestMapping("querybackSH")
	@ResponseBody
	public List<Map<String,Object>> querybackSH(){
		List<Map<String,Object>> list=service.querybackSH(2);
		return list;
	}

	@RequestMapping("querybackSHMH")
	@ResponseBody
	public List<Map<String,Object>> querybackSHMH(String bookname){
		List<Map<String,Object>> list=service.querybackSHMH(2,bookname);
		return list;
	}
}
