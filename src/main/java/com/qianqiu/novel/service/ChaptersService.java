package com.qianqiu.novel.service;

import java.util.List;

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
}
