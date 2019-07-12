package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.*;
import com.qianqiu.novel.service.BooksService;
import com.qianqiu.novel.service.ChaptersService;
import com.qianqiu.novel.service.RollsService;
import com.qianqiu.novel.service.SiteService;
import com.qianqiu.novel.utils.FileUtil;
import com.qianqiu.novel.utils.MyUtil;
import com.qianqiu.novel.utils.PdfUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("books")
public class BooksController {

	@Resource
	BooksService service;

	@Resource
	ChaptersService cs;

	@Resource
	RollsService rs;

	@Resource
	SiteService ss;

	@RequestMapping("bookqueryAll")
	@ResponseBody
	public List<Map<String,Object>> queryBook(){
		return service.queryAll();
	}

	@RequestMapping("likeBooks")
	public ModelAndView likebook(String kw){
		ModelAndView mv = new ModelAndView();
		mv.addObject("llist",service.likeBook(kw));
		mv.addObject("kw",kw);
		mv.setViewName("showAllBook");
		return mv;
	}

	@RequestMapping("typeBooks")
	@ResponseBody
	public Pages typebook(Books books, Rolls rolls, String kw, Integer orders,Integer labelid, Integer Index, Integer rows){
	    return service.typeBook(books,rolls,kw,orders,labelid,Index,rows);
	}

	@RequestMapping("queryBookById")
	@ResponseBody
	public Books queryBookById(Integer bookid){
		return service.queryBybookid(bookid);
	}

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
	public String queryAll(Model m,Integer bookid,HttpSession session){
		Users user = (Users) session.getAttribute("user");
		if(user!=null && user.getVip()>0) {
			service.addClick(bookid);
		}
		m.addAttribute("list",service.find(bookid));
        m.addAttribute("query",service.queryChapter(bookid));
        m.addAttribute("queryC",cs.queryBuy(bookid,null, MyUtil.getuserid(session)));
        m.addAttribute("pink",service.queryPink(bookid));
		m.addAttribute("site",ss.findAll());
		m.addAttribute("label",service.queryLabel(bookid));
        return "book";
	}

	@RequestMapping("queryMoney")
	public String queryMoney(Model m,Integer bookid){

		m.addAttribute("list",service.find(bookid));

		return "take";
	}
	@RequestMapping("queryMoneyRoll")
	@ResponseBody
	public List<Map<String,Object>> queryMoneyRoll(Integer bookid,HttpSession session){
		List<Map<String,Object>> list = rs.findByBookid(bookid);
		for(Map<String,Object> map:list){
			map.put("chaps",cs.queryBuy(null,Integer.parseInt(map.get("rollid").toString()),MyUtil.getuserid(session)));
		}
		return list;
	}

	@RequestMapping("getVoteAndGive")
	@ResponseBody
	public Map<String,Object> getVoteAndGive(Integer bookid){
		return service.find(bookid).get(0);
	}

	@RequestMapping("download")
	public void download(Integer bookid, HttpServletResponse response){
		Map<String,Object> book = service.find(bookid).get(0);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> map : rs.findByBookid(bookid)){
			if(Integer.parseInt(map.get("isvip").toString())==0) {
				map.put("chapters", cs.queryBuy(null, Integer.parseInt(map.get("rollid").toString()), null));
				list.add(map);
			}
		}
		book.put("rolls",list);

		try {
			PdfUtil.writePdf(book,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("queryRollAndChap")
	@ResponseBody
	public List<Map<String,Object>> queryRollAndChap(Integer bookid,HttpSession session){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> map : rs.findByBookid(bookid)){
			map.put("chapters", cs.queryBuy(null, Integer.parseInt(map.get("rollid").toString()), MyUtil.getuserid(session)));
			list.add(map);
		}
		return list;
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

	@RequestMapping("queryPage")
	@ResponseBody
	public Pages queryPage(Integer page,Integer rows,String bookname,Integer putaway,Integer state,Integer choose){
		return service.queryPage(page, rows, bookname, putaway, state,choose);
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
