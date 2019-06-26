package com.qianqiu.novel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qianqiu.novel.dao.IRollsDAO;
import com.qianqiu.novel.entity.Rolls;

import javax.annotation.Resource;

@Service
public class RollsService {

	@Resource
	IRollsDAO dao;

	public Integer add(Rolls rolls){
		return dao.add(rolls);
	}

	public List<Rolls> findByBookid(Integer bookid){
		return dao.findByBookid(bookid);
	}
}
