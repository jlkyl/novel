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
	public Integer updChapterInfo(String chaptername, String url,Integer state,Integer chapterid){
		return dao.updChapterInfo(chaptername,url,state,chapterid);
	}

	public Integer updChapDel(Integer state,Integer chapterid){
		return dao.updChapDel(state,chapterid);
	}

	public List<Map<String,Object>> queryAll(Integer bookid){
		return dao.queryAll(bookid);
	}

	public List<Map<String,Object>> queryChapter(Integer bookid){
		return dao.queryChapter(bookid);
	}

	public Chapters queryId(Integer chapterId){
		return  dao.queryId(chapterId);
	}

	public List<Chapters> upDow(Integer chapterid,Integer rollid){
		return dao.upDow(chapterid,rollid);
	}
}
