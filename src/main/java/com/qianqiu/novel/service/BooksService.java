package com.qianqiu.novel.service;

import java.util.List;
import java.util.Map;

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
}
