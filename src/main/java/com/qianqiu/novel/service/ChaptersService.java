package com.qianqiu.novel.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.qianqiu.novel.dao.IChaptersDAO;
import com.qianqiu.novel.entity.Chapters;

import javax.annotation.Resource;

@Service
public class ChaptersService {

	@Resource
	IChaptersDAO dao;

	public Integer add(Chapters chapters){
		return dao.add(chapters);
	}

	public Chapters findByName(String chaptername,Integer bookid){
		return dao.findByName(chaptername, bookid);
	}

	public Integer getOrder(Integer bookid){
		return dao.getOrder(bookid);
	}

	public List<Chapters> findByRollid(Integer rollid){
		return dao.findByRollid(rollid);
	}

	public List<Chapters> findName(Integer rollid,Integer state){
		return dao.findName(rollid,state);
	}
	//修改章节状态
	public Integer updChapter(Integer state,Integer chapterid){
		return dao.updChapter(state,chapterid);
	}
	//根据ID查询章节信息
	public Chapters queryById(Integer chapterid){return dao.queryById(chapterid);}
	//保存或者修改章节信息
	public Integer updChapterInfo(String chaptername, String url,Integer state,Integer chapterid,Integer wordnum){
		return dao.updChapterInfo(chaptername,url,state,chapterid,wordnum);
	}
	public List<Chapters> findAll(Integer bookid,Integer state){return dao.findAll(bookid,state);}

	public Integer updChapDel(Integer state,Integer chapterid){
		return dao.updChapDel(state,chapterid);
	}

	public List<Map<String,Object>> queryAll(Integer bookid){
		return dao.queryAll(bookid);
	}

	public List<Map<String,Object>> queryChapter(Integer bookid){
		return dao.queryChapter(bookid);
	}

	public Map<String,Object> queryId(Integer chapterId,Integer userid){
		return  dao.queryId(chapterId,userid);
	}

	public List<Chapters> upDow(Integer chapterid,Integer rollid){
		return dao.upDow(chapterid,rollid);
	}

	public List<Map<String,Object>> queryBuy(Integer bookid,Integer rollid,Integer userid){
		return dao.queryBuy(bookid,rollid, userid);
	}
	public List<Map<String,Object>> queryBrowses(Integer userid){
		return dao.queryBrowses(userid);
	}
	public Integer addBrowses(Integer userid,Integer chapid){
		return dao.addBrowses(userid, chapid);
	}
	public Integer updBrowses(Integer userid,Integer chapid){
		return dao.updBrowses(userid, chapid);
	}
	public Integer getChapid(Integer userid,Integer chapid,Integer bookid){
		return dao.getChapid(userid, chapid, bookid);
	}
	//后台审核查询
	public List<Map<String,Object>> querybackSH(Integer state){return dao.querybackSH(state);}
	//后台审核模糊查询
	public List<Map<String,Object>> querybackSHMH(Integer state,String bookname){
		return dao.querybackSHMH(state,bookname);
	}
	public  List<Map<String,Object>> cxsy(){
		return  dao.cxsy();
	}
}
