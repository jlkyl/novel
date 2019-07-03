package com.qianqiu.novel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

import java.util.Date;

@Data
public class Site implements Serializable {
	private Integer siteid;
	private String sitename;
	private String siteurl;
	private String principal;
	private String phone;
	private String email;
	private Integer operateeid;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date operatedate;
	private String empname;
}
