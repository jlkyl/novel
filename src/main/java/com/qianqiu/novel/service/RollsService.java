package com.qianqiu.novel.service;

import java.util.List;
import java.util.Map;

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

	public List<Map<String,Object>> findByBookid(Integer bookid){
		return dao.findByBookid(bookid);
	}
	//查询书籍中的书卷名是否存在
	public Rolls queryRollname(String rollname,Integer bookid){return dao.queryRollname(rollname,bookid);}

	public Integer updvip(Integer isvip,Integer rollid){
		return dao.updvip(isvip, rollid);
	}
	public Rolls queryId(Integer rollid){
		return dao.queryId(rollid);
	}

	public List<Map<String,Object>> findByBookid001(Integer bookid){
		return dao.findByBookid001(bookid);
	}

}
