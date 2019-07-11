package com.qianqiu.novel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Booktype {

    private Integer typeid;
    private String typename;
    private String icon;
    private Integer parentid;
    private Integer operateeid;
    private Date operatedate;
    private Integer _parentid;

}
