package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Books;
import com.qianqiu.novel.entity.Chapters;
import com.qianqiu.novel.service.BooksService;
import com.qianqiu.novel.entity.Rolls;
import com.qianqiu.novel.service.ChaptersService;
import com.qianqiu.novel.utils.FileUtil;
import com.qianqiu.novel.service.RollsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

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
	public String queryId(Model m,Integer chapterId,Integer rollid){

		m.addAttribute("queryId",service.queryId(chapterId,rollid));

		String read = 	FileUtil.read(service.queryId(chapterId,rollid).getUrl());

		read = read.replaceAll("(\r\n|\n)","</p><p>");

		m.addAttribute("str",	read);

		System.out.println(read);

		return "lookBook";
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

	@RequestMapping("queryName")
	@ResponseBody
	public List<Chapters> queryName(Integer rollid){

		return service.findName(rollid,1);
	}

	@RequestMapping("queryName01")
	@ResponseBody
	public List<Chapters> queryName01(Integer rollid){

		return service.findName(rollid,0);
	}

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
	public boolean updChapterInfo(String chaptername,Integer chapterid,Integer state,String txt,HttpSession session){
		//书名
		Books books=(Books) session.getAttribute("BOOK");
		Integer bookid=books.getBookid();
		String bookname=booksService.querybyId(bookid).getBookname();

		Chapters chapters=service.queryById(chapterid);
		System.out.println("显示书籍章节信息"+chapters);
		System.out.println("IDIDIDIDIDIDIDID"+chapters.getRollid());
		//卷名
		Rolls rolls=rollsService.queryId(chapters.getRollid());
		System.out.println("1111111111"+rolls);
		String rollname=rolls.getRollname();
		String oldChaptername=chapters.getUrl();
		FileUtil.upFileName(oldChaptername,bookname+"\\"+rollname+"\\"+chaptername+".txt");
		FileUtil.write(bookname+"\\"+rollname+"\\"+chaptername+".txt",txt);
		String urll=bookname+"\\"+rollname+"\\"+chaptername+".txt";
		Integer i=service.updChapterInfo(chaptername,urll,state,chapterid);
		if(i!=null){
			return true;
		}else {
			return false;
		}
	}

	@RequestMapping("updChapDel")
	@ResponseBody
	public boolean  updChapDel(Integer state,Integer chapterid){
		Integer i=service.updChapDel(state,chapterid);
		return true;
	}
}
