package com.qianqiu.novel.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.qianqiu.novel.dao.IWeekpushDAO;
import com.qianqiu.novel.entity.Weekpush;

import javax.annotation.Resource;

@Service
public class WeekpushService {

	@Resource
	IWeekpushDAO dao;

	public Integer add(Weekpush weekpush){
		return dao.add(weekpush);
	}

	public Integer getCount(){
		return dao.getCount();
	}

	public Integer getbookidCount(Integer bookid){return dao.getbookidCount(bookid);}

	public List<Map<String,Object>> findAll(Integer all,Integer userid){
		return dao.findAll(all, userid);
	}

}
