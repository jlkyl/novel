package com.qianqiu.novel.entity;

import lombok.Data;

import java.io.Serializable;

import java.util.Date;

@Data
public class Rolls implements Serializable {
	private Integer rollid;
	private String rollname;
	private Integer bookid;
	private Date createtime;
	private Integer isvip;
	private String url;
}
