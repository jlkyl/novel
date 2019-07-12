package com.qianqiu.novel.service;

import java.util.List;
import java.util.Map;

import com.qianqiu.novel.entity.Booktype;
import com.qianqiu.novel.entity.Chapters;
import com.qianqiu.novel.entity.Labels;
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
	//书籍添加标签
	public Integer addLabel(Integer bookid,Integer labelid){
		return dao.addLabel(bookid, labelid);
	}

	public Integer getChapterNums(Integer bookid){
		return dao.getChapterNums(bookid);
	}

	public List<Map<String,Object>> findAll(Integer userid){return dao.findAll(userid);}
	//显示章节
	public List<Map<String,Object>> booksAll(Integer userid){return dao.booksAll(userid);}
	//查询更新的章节
	public Chapters queryTime(Integer bookid){return dao.queryTime(bookid);}
	//修改书籍的状态（是否完结）
	public Integer updBookstate(Integer bookid){return dao.updBookstate(bookid);}
	//根据ID查询书籍名称
	public  Books querybyId(Integer bookid){return dao.querybyId(bookid);}
	//修改书籍状态 上架1，未上架0
	public Integer updPutaway(Integer putaway,Integer bookid){
		return dao.updPutaway(putaway,bookid);
	}

	public List<Map<String,Object>> query(Integer choose,Integer putaway,Integer state){
		return dao.query(choose,putaway,state,null);
	}

    /*public List<Map<String,Object>> queryUpdate(){
        return dao.queryUpdate();
    }*/

	public List<Map<String,Object>> queryAuthor(){
		return dao.queryAuthor();
	}

	/*public List<Map<String,Object>> queryAll(Integer bookid){
		return dao.query(null,null,null,bookid);
	}*/

    public List<Map<String,Object>> queryChapter(Integer bookid){
        return dao.queryChapter(bookid);
    }

    public  List<Chapters> queryC(){
	    return dao.queryC();
    }

	public List<Map<String,Object>> find(Integer bookid){
		return dao.query(null,null,null,bookid);
	}

	public Pages typeBook(Books books, Rolls rolls, String kw,Integer orders,Integer labelid,Integer pageNum, Integer pageSize){
        Pages pages = new Pages();
        pages.setRows(dao.typeBook(books,rolls,kw,orders,labelid,(pageNum-1)*pageSize,pageSize));
        pages.setTotal(dao.getC());
        return pages;
	}

    public Books queryBybookid(Integer bookid){
	    return dao.queryBybookid(bookid);
    }

	//作品修改
	public Integer updBookname(Books b){
		return dao.updBookname(b);
	}
	//查询已发布章节的字数，如果达到要求，则本书自动上架
	public Integer queryWordNum(Integer state,Integer bookid){
		return dao.queryWordNum(state,bookid);
	}
	//查询标签
	public List<Labels> queryLabels(){return dao.queryLabels();}
	//订阅查询
	public List<Map<String,Object>> queryExp(Integer exptypeid,Integer bookid){
		return dao.queryExp(exptypeid, bookid);
	}
	//订阅查询书籍
	public List<Map<String,Object>> queryExpbook(Integer exptypeid,Integer userid){
		return dao.queryExpbook(exptypeid, userid);
	}
	//查询章节金额收入
	public List<Map<String,Object>> queryExpmoney(Integer exptypeid,Integer userid,String bookname){
		return dao.queryExpmoney(exptypeid, userid,bookname);
	}

	//查询打赏记录
	public List<Map<String,Object>> queryExpbook02(Integer exptypeid,Integer Userid){
		return dao.queryExpbook02(exptypeid, Userid);
	}
	//根据BOOKID查询标签
	public List<Labels> queryLabel(Integer bookid){
		return dao.queryLabel(bookid);
	}
	//根据typeid 查询类型名
	public List<Booktype> queryTypename(Integer typeid){
		return dao.queryTypename(typeid);
	}
	//根据作家查询周推
	public List<Map<String,Object>> queryWeek(Integer userid){
		return dao.queryWeek(userid);
	}

	public Integer addClick(Integer bookid){
    	return dao.addClick(bookid);
	}
	public List<Map<String,Object>> queryPink(Integer bookid){
    	return dao.queryPink(bookid);
	}
	public Pages queryPage(Integer pageNum,Integer pageSize,String bookname,Integer putaway,Integer state,Integer choose){
    	Pages pages = new Pages();
    	pages.setTotal(dao.getCount(bookname, putaway, state));
    	pages.setRows(dao.queryPage((pageNum-1)*pageSize, pageSize, bookname, putaway, state,choose));
    	return pages;
	}
}
