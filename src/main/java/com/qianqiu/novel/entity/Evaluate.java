package com.qianqiu.novel.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Evaluate {

    private Integer evaid;
    private Integer userid;
    private Integer bookid;
    private String content;
    private Integer level;
    private Date evatime;

}
