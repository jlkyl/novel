package com.qianqiu.novel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qianqiu.novel.dao.ISiteDAO;
import com.qianqiu.novel.entity.Site;

import javax.annotation.Resource;

@Service
public class SiteService {

	@Resource
	ISiteDAO dao;

	public List<Site> findAll(){
		return dao.findAll();
	}

	public Integer add(Site site){
		return dao.add(site);
	}

	public Integer update(Site site){
		return dao.update(site);
	}
}
