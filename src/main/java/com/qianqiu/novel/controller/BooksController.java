package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.*;
import com.qianqiu.novel.service.BooksService;
import com.qianqiu.novel.utils.FileUtil;
import com.qianqiu.novel.utils.MyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
			FileUtil.File(books.getBookname());
			return true;
		}
	}

	@RequestMapping(value="queryB")
    @ResponseBody
	public List<Books> queryB(){

		return service.queryB();
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

	@RequestMapping("updBookstate")
    @ResponseBody
	public boolean updBookstate(Integer bookid){
	    service.updBookstate(bookid);
	    return true;
    }

    @RequestMapping("updBookname")
	@ResponseBody
    public boolean updBookname(HttpSession session, Books books, MultipartFile[] file){
		String cover = "";
		try {
			cover=FileUtil.fileUpload(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(cover!=""){
			books.setCover(cover);
		}
		Integer i=service.updBookname(books);

		if(i!=null){
			return true;
		}else {
			return false;
		}

	}

	@RequestMapping("updPutaway")
	@ResponseBody
    public Integer updPutaway(Integer putaway,Integer bookid){
		return service.updPutaway(putaway, bookid);
	}

	@RequestMapping("autoPutaway")
	@ResponseBody
	public boolean autoPutaway(Integer bookid){
		Integer i=service.queryWordNum(0,bookid);
		if(i!=null && i>3000){
			service.updPutaway(1,bookid);
			return true;
		}
		return false;

	}

	@RequestMapping("queryAll")
	public String queryAll(Model m,Integer bookid){
		m.addAttribute("list",service.queryAll(bookid));

        m.addAttribute("query",service.queryChapter(bookid));

        m.addAttribute("queryC",service.queryC());

        return "book";
	}

	@RequestMapping("query")
	public String query(Model m,Integer bookid){

		m.addAttribute("list",service.find(bookid));

		m.addAttribute("str",	FileUtil.read((String) service.find(bookid).get(0).get("url")));

		return "lookBook";
	}

	@RequestMapping("queryLabels")
	@ResponseBody
	public List<Labels> queryLabels(){
		System.out.println(service.queryLabels());
		return service.queryLabels();

	}

	@RequestMapping("addLabel")
	@ResponseBody
	public boolean addLabel(Integer[] labelid,String bookname){
		Books b=service.findByName(bookname);
		for(int i=0;i<labelid.length;i++){
			service.addLabel(b.getBookid(),labelid[i]);
		}
		return true;
	}

	@RequestMapping("queryExp")
	@ResponseBody
	public List<Map<String,Object>> queryExp(Integer exptypeid,Integer bookid){
		return service.queryExp(exptypeid, bookid);
	}

	@RequestMapping("queryExpbook")
	@ResponseBody
	public Layui queryExpbook(HttpSession session){
		Layui layui = new Layui();
		layui.setData(service.queryExpbook(3, MyUtil.getuserid(session)));
		return layui;
	}

	//查询章节金额收入

	@RequestMapping("queryExpmoney")
	@ResponseBody
	public List<Map<String,Object>> queryExpmoney(Integer exptypeid,HttpSession session,String bookname){

		return service.queryExpmoney(exptypeid,MyUtil.getuserid(session),bookname);
	}

	@RequestMapping("queryExpbook02")
	@ResponseBody
	public List<Map<String,Object>> queryExpbook02(Integer exptypeid,HttpSession session){

		return service.queryExpbook02(exptypeid,MyUtil.getuserid(session));
	}

	@RequestMapping("queryLabel")
	@ResponseBody
	public List<Labels> queryLabel(HttpSession session){
		Books books=(Books)session.getAttribute("BOOK");
		Integer bookid=books.getBookid();
		return service.queryLabel(bookid);
	}

	@RequestMapping("queryTypename")
	@ResponseBody
	public List<Booktype> queryTypename(HttpSession session){
		Books books=(Books)session.getAttribute("BOOK");
		Integer typeid=books.getTypeid();
		return service.queryTypename(typeid);

	}
	@RequestMapping("queryWeek")
	@ResponseBody
	public List<Map<String,Object>> queryWeek(HttpSession session){
		return service.queryWeek(MyUtil.getuserid(session));
	}
}
