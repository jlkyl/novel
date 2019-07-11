package com.qianqiu.novel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Menus implements Serializable {
	private Integer id;
	private String text;
	private String url;
	private Integer parentId;
	private Integer operateeid;
	private Date operatedate;
	private String iconCls;
	private String empname;
}
