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

	public List<Map<String,Object>> queryAll(Integer bookid){
		return dao.queryAll(bookid);
	}

	public List<Map<String,Object>> queryChapter(Integer bookid){
		return dao.queryChapter(bookid);
	}

	public Chapters queryId(Integer chapterId,Integer rollid){
		return  dao.queryId(chapterId,rollid);
	}

	public List<Chapters> upDow(Integer chapterid,Integer rollid){
		return dao.upDow(chapterid,rollid);
	}
}
