package com.qianqiu.novel.service;

import java.util.List;
import java.util.Map;

import com.qianqiu.novel.entity.Booktype;
import com.qianqiu.novel.entity.Chapters;
import com.qianqiu.novel.entity.Pages;
import com.qianqiu.novel.entity.Rolls;
import org.springframework.stereotype.Service;

import com.qianqiu.novel.dao.IBooksDAO;
import com.qianqiu.novel.entity.Books;

import javax.annotation.Resource;

@Service
public class BooksService {

	@Resource
	IBooksDAO dao;

	public List<Map<String,Object>> queryAll(){
		return dao.queryBook();
	}

	public List<Map<String,Object>> likeBook(String kw){
		return dao.likeBook(kw);
	}
	public List<Books> queryB(){
		return dao.queryB();
	}

	public Integer add(Books books){
		return dao.add(books);
	}

	public Books findByName(String bookname){
		return dao.findByName(bookname);
	}

	public Integer addLabel(Integer bookid,Integer labelid){
		return dao.addLabel(bookid, labelid);
	}

	public Integer getChapterNums(Integer bookid){
		return dao.getChapterNums(bookid);
	}

	public List<Map<String,Object>> findAll(Integer userid){
		return dao.findAll(userid);
	}
	//显示章节
	public List<Map<String,Object>> booksAll(Integer userid){return dao.booksAll(userid);}
	//查询更新的章节
	public Chapters queryTime(Integer bookid){return dao.queryTime(bookid);}
	//修改书籍的状态（是否完结）
	public Integer updBookstate(Integer bookid){return dao.updBookstate(bookid);}
	//根据ID查询书籍名称
	public  Books querybyId(Integer bookid){return dao.querybyId(bookid);}

	public Integer updPutaway(Integer putaway,Integer bookid){
		return dao.updPutaway(putaway,bookid);
	}

	public List<Map<String,Object>> query(Integer choose,Integer putaway,Integer state){
		return dao.query(choose,putaway,state);
	}

	public List<Map<String,Object>> queryUpdate(){
		return dao.queryUpdate();
	}

	public List<Map<String,Object>> queryAuthor(){
		return dao.queryAuthor();
	}

	public List<Map<String,Object>> queryAll(Integer bookid){
		return dao.queryAll(bookid);
	}

    public List<Map<String,Object>> queryChapter(Integer bookid){
        return dao.queryChapter(bookid);
    }

    public  List<Chapters> queryC(){
	    return dao.queryC();
    }

	public List<Map<String,Object>> find(Integer bookid){
		return dao.queryAll(bookid);
	}

	public Integer updBookname(String bookname,Integer bookid){
		return dao.updBookname(bookname,bookid);
	}

	public Pages typeBook(Books books, Rolls rolls, String kw,Integer orders,Integer labelid,Integer pageNum, Integer pageSize){
        Pages pages = new Pages();
        pages.setRows(dao.typeBook(books,rolls,kw,orders,labelid,(pageNum-1)*pageSize,pageSize));
        pages.setTotal(dao.getCount());
        return pages;
	}

    public Books queryBybookid(Integer bookid){
	    return dao.queryBybookid(bookid);
    }
}
