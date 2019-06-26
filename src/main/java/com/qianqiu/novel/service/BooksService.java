package com.qianqiu.novel.service;

import java.util.List;

import com.qianqiu.novel.entity.Booktype;
import org.springframework.stereotype.Service;

import com.qianqiu.novel.dao.IBooksDAO;
import com.qianqiu.novel.entity.Books;

import javax.annotation.Resource;

@Service
public class BooksService {

	@Resource
	IBooksDAO dao;

	public Integer add(Books books){
		return dao.add(books);
	}

	public Books findByName(String bookname){
		return dao.findByName(bookname);
	}

	public Integer addLabel(Integer bookid,Integer labelid){
		return dao.addLabel(bookid, labelid);
	}

	public Integer getChapterNums(Integer bookid){
		return dao.getChapterNums(bookid);
	}
}
