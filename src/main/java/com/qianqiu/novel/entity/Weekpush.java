package com.qianqiu.novel.entity;

import lombok.Data;

import java.io.Serializable;

import java.util.Date;

@Data
public class Weekpush implements Serializable {
	private Integer pushid;
	private Integer bookid;
	private Integer potential;
	private Integer money;
	private Date pushtime;
}
