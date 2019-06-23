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

}
