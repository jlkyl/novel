package com.qianqiu.novel.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Votes {

    private Integer voteid;
    private Integer userid;
    private Integer bookid;
    private Integer nums;
    private Date votetime;

}
